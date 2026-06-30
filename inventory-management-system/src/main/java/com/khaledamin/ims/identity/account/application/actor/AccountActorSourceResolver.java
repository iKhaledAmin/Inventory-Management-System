package com.khaledamin.ims.identity.account.application.actor;


import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.core.exception.IdentityTechnicalException;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorSource;
import com.khaledamin.ims.identity.core.model.ActorType;
import com.khaledamin.ims.identity.core.resolver.ActorSourceResolver;

public class AccountActorSourceResolver implements ActorSourceResolver {

    @Override
    public ActorType getType() {
        return ActorType.ACCOUNT;
    }

    @Override
    public Actor resolve(ActorSource source) {

        if (!(source instanceof Account account)) {
            throw IdentityTechnicalException.sourceTypeMismatch(
                    Account.class,
                    source.getClass()
            );
        }
        return new AccountActor(
                ActorCode.of(account.getAccountCode()),
                account.getRoleNames(),
                account.getPermissions()
        );
    }
}
