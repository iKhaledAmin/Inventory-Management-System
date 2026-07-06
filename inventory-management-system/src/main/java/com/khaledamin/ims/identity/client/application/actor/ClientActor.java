package com.khaledamin.ims.identity.client.application.actor;



import com.khaledamin.ims.identity.core.model.AbstractActor;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.Getter;

import java.util.Set;

@Getter
public class ClientActor extends AbstractActor {

    private final Set<String> authorities;

    public ClientActor(ActorCode actorCode, Set<String> authorities) {
        super(ActorIdentity.of(ActorType.CLIENT, actorCode));
        this.authorities = authorities;
    }

    @Override
    public boolean hasAuthority(String authority) {
        return authorities.contains(authority);
    }
}