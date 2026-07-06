package com.khaledamin.ims.identity.account.application.actor;



import com.khaledamin.ims.auth.account.infrastructure.principal.AccountPrincipal;
import com.khaledamin.ims.identity.core.exception.IdentityTechnicalException;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorType;
import com.khaledamin.ims.identity.core.resolver.ActorPrincipalResolver;
import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import org.springframework.stereotype.Component;

@Component
public class AccountActorPrincipalResolver implements ActorPrincipalResolver {

    @Override
    public ActorType getType() {
        return ActorType.ACCOUNT;
    }

    @Override
    public Actor resolve(AuthenticatedPrincipal principal) {

        if (!(principal instanceof AccountPrincipal account)) {
            throw IdentityTechnicalException.principalTypeMismatch(
                    AccountPrincipal.class,
                    principal.getClass()
            );
        }

        return new AccountActor(
                account.getActorCode(),
                account.getRoles(),
                account.getAuthorities()
        );
    }
}