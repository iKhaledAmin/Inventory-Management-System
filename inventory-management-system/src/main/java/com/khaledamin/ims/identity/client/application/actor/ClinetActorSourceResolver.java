package com.khaledamin.ims.identity.client.application.actor;

import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.core.exception.IdentityTechnicalException;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorSource;
import com.khaledamin.ims.identity.core.model.ActorType;
import com.khaledamin.ims.identity.core.resolver.ActorSourceResolver;

public class ClinetActorSourceResolver implements ActorSourceResolver {
    @Override
    public ActorType getType() {
        return ActorType.CLIENT;
    }

    @Override
    public Actor resolve(ActorSource source) {
        if (!(source instanceof Client client)) {
            throw IdentityTechnicalException.sourceTypeMismatch(
                    Client.class,
                    source.getClass()
            );
        }

        return new ClientActor(
                ActorCode.of(client.getClientCode()),
                client.getAuthority()
        );
    }
}
