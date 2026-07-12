package com.khaledamin.ims.auth.client.infrastructure.principal;

import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import com.khaledamin.ims.auth.security.core.principal.PrincipalResolver;
import com.khaledamin.ims.auth.security.core.jwt.JwtPayload;
import com.khaledamin.ims.auth.security.exception.CustomSecurityException;
import com.khaledamin.ims.identity.client.application.service.ClientQueryService;
import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientPrincipalResolver implements PrincipalResolver {

    private final ClientQueryService clientQueryService;

    @Override
    public ActorType getType() {
        return ActorType.CLIENT;
    }

    @Override
    public AuthenticatedPrincipal resolve(JwtPayload payload) {

        Client client = clientQueryService.getOptionalByClientId(payload.getSubject())
                .orElseThrow(() ->
                        CustomSecurityException.principalNotFound("Client not found")
                                .withDebugDetails("clientId", payload.getSubject())
                                .withDebugDetails("actorCode", payload.getActorCode())
                                .withDebugDetails("actorType", payload.getActorType().name())
                );

        return ClientPrincipal.of(
                payload.getSubject(),
                payload.getActorCode().toString(),
                client.getStatus(),
                payload.getAuthorities()
        );
    }
}