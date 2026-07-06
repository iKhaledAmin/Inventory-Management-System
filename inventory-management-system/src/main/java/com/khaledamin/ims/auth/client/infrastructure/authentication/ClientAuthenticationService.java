package com.khaledamin.ims.auth.client.infrastructure.authentication;

import com.khaledamin.ims.auth.client.infrastructure.principal.ClientPrincipal;
import com.khaledamin.ims.auth.security.core.authentication.CredentialAuthenticationService;
import com.khaledamin.ims.auth.security.exception.AuthenticationException;
import com.khaledamin.ims.identity.client.application.service.ClientQueryService;
import com.khaledamin.ims.identity.client.domain.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientAuthenticationService implements CredentialAuthenticationService<ClientPrincipal> {

    private final ClientQueryService clientQueryService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ClientPrincipal authenticate(String clientSubject, String clientSecret) {

        // 1. Load client
        Client client = clientQueryService.getOptionalByClientId(clientSubject)
                .orElseThrow(() -> AuthenticationException.invalidCredentials()
                        .withDebugDetails("problem", "Client not found")
                );

        // 2. Validate secret
        if (!passwordEncoder.matches(clientSecret, client.getClientSecretHash())) {
            throw AuthenticationException.invalidCredentials()
                    .withClientDetails("reason", "Invalid client id or client secret")
                    .withDebugDetails("problem", "Invalid client secret");
        }

        // 3. Validate state
        if (client.getStatus().isLocked()) {
            throw AuthenticationException.principalLocked("Client")
                    .withDebugDetails("problem", "Client is locked");
        }

        if (!client.getStatus().isActive()) {
            throw AuthenticationException.principalInactive("Client")
                    .withDebugDetails("problem", "Client is inactive");
        }

        // 4. Build principal
        return ClientPrincipal.of(
                client.getClientId(),
                client.getClientCode(),
                client.getStatus(),
                client.getAuthority()
        );
    }
}