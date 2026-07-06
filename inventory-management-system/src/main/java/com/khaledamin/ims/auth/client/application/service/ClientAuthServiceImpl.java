package com.khaledamin.ims.auth.client.application.service;

import com.khaledamin.ims.auth.client.infrastructure.authentication.ClientAuthenticationService;
import com.khaledamin.ims.auth.client.infrastructure.principal.ClientPrincipal;
import com.khaledamin.ims.auth.client.api.dto.ClientTokenRequest;
import com.khaledamin.ims.auth.client.api.dto.ClientTokenResponse;
import com.khaledamin.ims.auth.client.api.mapper.ClientAuthMapper;
import com.khaledamin.ims.auth.security.core.jwt.JwtService;
import com.khaledamin.ims.core.exception.security.SecurityException;
import com.khaledamin.ims.core.logging.core.ActorLoggingContext;
import com.khaledamin.ims.core.logging.event.SecurityEventLogger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientAuthServiceImpl implements ClientAuthService {

    private final ClientAuthenticationService authenticationService;

    private final JwtService jwtService;

    private final ClientAuthMapper clientAuthMapper;

    private final SecurityEventLogger securityEventLogger;

    @Override
    @Transactional
    public ClientTokenResponse generateToken(ClientTokenRequest request) {

        try {

            ClientPrincipal principal = authenticationService.authenticate(
                    request.getClientId(),
                    request.getClientSecret()
            );

            String jwtToken = jwtService.generateToken(principal);

            // Set actor context
            ActorLoggingContext.put(principal);

            // Audit
            securityEventLogger.loginSucceeded(principal);

            return clientAuthMapper.toTokenResponse(
                    jwtToken,
                    principal
            );

        } catch (SecurityException ex) {

            securityEventLogger.loginFailed(
                    request.getClientId(),
                    ex
            );

            throw ex;
        }
    }
}