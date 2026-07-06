package com.khaledamin.ims.identity.client.application.actor;

import com.khaledamin.ims.auth.client.infrastructure.principal.ClientPrincipal;
import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import com.khaledamin.ims.identity.core.exception.IdentityTechnicalException;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorType;
import com.khaledamin.ims.identity.core.resolver.ActorPrincipalResolver;
import org.springframework.stereotype.Component;

@Component
public class ClientActorPrincipalResolver implements ActorPrincipalResolver {

    @Override
    public ActorType getType() {
        return ActorType.CLIENT;
    }

    @Override
    public Actor resolve(AuthenticatedPrincipal principal) {

        if (!(principal instanceof ClientPrincipal client)) {
            throw IdentityTechnicalException.principalTypeMismatch(
                    ClientPrincipal.class,
                    principal.getClass()
            );
        }

        return new ClientActor(
                client.getActorCode(),
                client.getAuthorities()
        );
    }
}