package com.khaledamin.ims.identity.account.application.actor;

import com.khaledamin.ims.identity.core.model.AbstractActor;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.identity.core.model.ActorType;

import java.util.Set;

public class AccountActor extends AbstractActor {

    private final Set<String> roles;
    private final Set<String> authorities;

    public AccountActor(ActorCode actorCode, Set<String> roles , Set<String> authorities) {
        super(
                ActorIdentity.of(ActorType.ACCOUNT, actorCode)
        );

        this.roles = roles;
        this.authorities = authorities;
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public boolean hasAnyRole(String... roles) {
        for (String role : roles) {
            if (hasRole(role)) return true;
        }
        return false;
    }

    @Override
    public boolean hasAuthority(String authority) {
        return authorities.contains(authority);
    }
}