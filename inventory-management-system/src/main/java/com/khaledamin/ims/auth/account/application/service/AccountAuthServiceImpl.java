package com.khaledamin.ims.auth.account.application.service;

import com.khaledamin.ims.auth.account.api.dto.*;
import com.khaledamin.ims.auth.account.api.mapper.AccountAuthMapper;
import com.khaledamin.ims.auth.account.application.config.AuthenticationProperties;
import com.khaledamin.ims.auth.account.exception.AuthException;
import com.khaledamin.ims.auth.security.core.jwt.JwtService;
import com.khaledamin.ims.auth.account.infrastructure.principal.AccountPrincipal;
import com.khaledamin.ims.auth.account.infrastructure.authentication.AccountAuthenticationService;
import com.khaledamin.ims.core.api.response.ApiActionResponse;
import com.khaledamin.ims.core.exception.technical.TechnicalException;
import com.khaledamin.ims.auth.security.exception.CustomSecurityException;
import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.core.logging.event.SecurityEventLogger;
import com.khaledamin.ims.core.logging.core.ActorLoggingContext;
import com.khaledamin.ims.email.application.port.in.EmailService;
import com.khaledamin.ims.email.domain.command.EmailCreateCommand;
import com.khaledamin.ims.email.domain.model.EmailTemplate;
import com.khaledamin.ims.email.infrastructure.config.EmailProperties;
import com.khaledamin.ims.identity.account.api.dto.AccountCreateRequest;
import com.khaledamin.ims.identity.account.application.service.AccountManagementService;
import com.khaledamin.ims.identity.account.application.service.AccountQueryService;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.value.EmailAddress;
import com.khaledamin.ims.identity.account.domain.value.RawPassword;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.verification.application.dto.VerificationResult;
import com.khaledamin.ims.verification.application.service.VerificationService;
import com.khaledamin.ims.verification.domain.model.TokenType;
import com.khaledamin.ims.verification.exception.VerificationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AccountAuthServiceImpl implements AccountAuthService {


    private final VerificationService verificationService;
    private final AccountManagementService accountManagementService;
    private final AccountQueryService accountQueryService;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AccountAuthMapper accountAuthMapper;
    private final AuthenticationProperties authProperties;
    private final EmailProperties emailProperties;
    private final AccountAuthenticationService authenticationService;
    private final SecurityEventLogger securityEventLogger;
    private final BusinessEventLogger businessEventLogger;



    @Override
    @Transactional
    public AccountRegistrationResponse register(AccountRegistrationRequest request){

        AccountCreateRequest createRequest = accountAuthMapper.toCreateRequest(request);

        Account newAccount = accountManagementService.create(createRequest);

        String activationCode = verificationService.generateToken(
                TokenType.ACCOUNT_ACTIVATION,
                newAccount.getActorIdentity()
        );

        sendActivationEmail(newAccount, activationCode);

        businessEventLogger.accountRegistered(newAccount.getActorCode().toString());

        return accountAuthMapper.toRegistrationResponse(newAccount);
    }

    @Override
    @Transactional
    public AccountActivationResponse activate(AccountActivationRequest request){

        EmailAddress emailAddress = EmailAddress.of(request.getEmailAddress());

        Account account = accountQueryService.getOptionalByEmail(emailAddress)
                .orElseThrow(() -> AuthException.activationFailed()
                        .withDebugDetails("reason", "Account not found for the given email address")
                        .withDebugDetails("emailAddress", request.getEmailAddress())
                );

        VerificationResult result = verifyAccountActivationToken(
                request.getActivationCode(),
                account.getActorIdentity()
        );

        ActorCode actorCode = result.target().getActorCode();

        Account activatedAccount = accountManagementService.activate(actorCode);

        return accountAuthMapper.toActivationResponse(activatedAccount);
    }



    @Override
    @Transactional
    public AccountLoginResponse login(AccountLoginRequest request) {

        try {

            AccountPrincipal principal = authenticationService.authenticate(
                    request.getUsername(),
                    request.getPassword()
            );

            accountManagementService.login(principal.getActorCode());

            String jwtToken = jwtService.generateToken(principal);

            // Set actor context
            ActorLoggingContext.put(principal);

            // Log login success
            securityEventLogger.loginSucceeded(principal);

            return accountAuthMapper.toLoginResponse(jwtToken, principal);

        } catch (CustomSecurityException ex) {

            securityEventLogger.loginFailed(
                    request.getUsername(),
                    ex
            );

            throw ex;
        }
    }

    @Transactional
    @Override
    public ApiActionResponse requestResetPassword(AccountResetPasswordRequest request) {

        EmailAddress emailAddress = EmailAddress.of(request.getEmailAddress());

        Optional<Account> optionalAccount = accountQueryService.getOptionalByEmail(emailAddress);
        if (optionalAccount.isEmpty()) {
            return ApiActionResponse.builder()
                    .message("If an account exists for this emailAddress address,you will receive a reset password emailAddress.")
                    .build();

        }

        Account account = optionalAccount.get();

        String resetCode = verificationService.generateToken(
                TokenType.ACCOUNT_RESET_PASSWORD,
                account.getActorIdentity()
        );

        sendResetPasswordEmail(account, resetCode);

        businessEventLogger.passwordResetRequested(
                account.getAccountCode()
        );

        return ApiActionResponse.builder()
                .message("If an account exists for this email address,you will receive a reset password email.")
                .build();
    }

    @Override
    @Transactional
    public ApiActionResponse resetPassword(AccountConfirmResetPasswordRequest request) {

        EmailAddress emailAddress = EmailAddress.of(request.getEmailAddress());

        Account account = accountQueryService.getOptionalByEmail(emailAddress)
                .orElseThrow(() -> AuthException.resetPasswordFailed()
                        .withDebugDetails("reason", "Account not found for the given email address")
                        .withDebugDetails("emailAddress", request.getEmailAddress())
                );

        // Validate token
        VerificationResult result = verifyResetPasswordToken(
                request.getResetCode(),
                account.getActorIdentity()
        );

        ActorCode actorCode = result.target().getActorCode();

        // Apply domain operation
        accountManagementService.resetPassword(
                actorCode,
                RawPassword.of(request.getPassword())
        );

        // Response
        return ApiActionResponse.builder()
                .message("Your password has been reset successfully.")
                .build();
    }


    // -------------------------------------- Helper methods --------------------------------------- //

    private VerificationResult verifyAccountActivationToken(String verificationCode, ActorIdentity target) {
        try {

            return verificationService.verifyToken(
                    verificationCode,
                    target,
                    TokenType.ACCOUNT_ACTIVATION
            );

        }
        catch (VerificationException e) {
            throw AuthException.activationFailed(e)
                    .withClientDetails("reason", e.getMessage());
        }
    }

    private VerificationResult verifyResetPasswordToken(String verificationCode, ActorIdentity target) {
        try {

            return verificationService.verifyToken(
                    verificationCode,
                    target,
                    TokenType.ACCOUNT_RESET_PASSWORD
            );

        }
        catch (VerificationException e) {
            throw AuthException.resetPasswordFailed(e)
                    .withClientDetails("reason", e.getMessage());
        }
    }


    private void sendActivationEmail(Account account, String activationCode) {

        String activationUrl = authProperties.activation().frontendUrl();
        String sender = emailProperties.sender().from();
        String replyTo = emailProperties.sender().replyTo();

        Map<String, Object> variables = Map.of(
                "username", account.getUsername(),
                "activationCode", activationCode,
                "activationUrl", activationUrl
        );

        EmailCreateCommand command = EmailCreateCommand.of(
                sender,
                account.getEmailAddress(),
                replyTo,
                Set.of(),
                Set.of(),
                EmailTemplate.ACCOUNT_ACTIVATION.getSubject(),
                EmailTemplate.ACCOUNT_ACTIVATION.getName()
        );

        try {
            emailService.sendEmail(command, variables);
        } catch (TechnicalException e) {
            // Intentionally ignored.
            // Email delivery failure is non-blocking.
            // The Email module already logs the failure and schedules retries.
        }

    }

    private void sendResetPasswordEmail(Account account, String resetCode) {
        String sender = emailProperties.sender().from();
        String replyTo = emailProperties.sender().replyTo();

        Map<String, Object> variables = Map.of(
                "username", account.getUsername(),
                "resetCode", resetCode
        );

        EmailCreateCommand command = EmailCreateCommand.of(
                sender,
                account.getEmailAddress(),
                replyTo,
                Set.of(),
                Set.of(),
                EmailTemplate.ACCOUNT_RESET_PASSWORD.getSubject(),
                EmailTemplate.ACCOUNT_RESET_PASSWORD.getName()
        );

        try {
            emailService.sendEmail(command, variables);
        } catch (TechnicalException e) {
            // Intentionally ignored.
            // Email delivery failure is non-blocking.
            // The Email module already logs the failure and schedules retries.
        }

    }

    // -------------------------------------- End Helper methods ----------------------------------- //
}
