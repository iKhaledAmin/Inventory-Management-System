package com.khaledamin.ims.auth.security.provider;

import com.khaledamin.ims.auth.security.exception.AuthenticationException;
import com.khaledamin.ims.auth.security.principal.account.AccountPrincipal;
import com.khaledamin.ims.identity.account.application.service.AccountQueryService;
import com.khaledamin.ims.identity.account.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountAuthenticationService implements CredentialAuthenticationService <AccountPrincipal> {

    private final AccountQueryService accountQueryService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountPrincipal authenticate(String username, String password) {

        Account account = accountQueryService.getOptionalByUsername(username)
                .orElseThrow(() -> AuthenticationException.invalidCredentials()
                        .withDebugDetails("reason", "Account not found")
                );

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw AuthenticationException.invalidCredentials()
                    .withDebugDetails("reason", "Invalid password");}

        if (account.getAccountStatus().isLocked()) {
            throw AuthenticationException.principalLocked("Account")
                    .withDebugDetails("reason", "Account is locked");
        }

        if (!account.getAccountStatus().isActive()) {
            throw AuthenticationException.principalInactive("Account")
                    .withDebugDetails("reason", "Account is inactive");
        }

        return AccountPrincipal.of(
                account.getUsername(),
                account.getAccountCode(),
                account.getAccountStatus(),
                account.getRoleNames(),
                account.getPermissions()
        );
    }
}