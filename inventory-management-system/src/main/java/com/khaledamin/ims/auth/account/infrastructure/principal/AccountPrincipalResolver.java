package com.khaledamin.ims.auth.account.infrastructure.principal;

import com.khaledamin.ims.auth.security.exception.CustomSecurityException;
import com.khaledamin.ims.identity.account.application.service.AccountQueryService;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.core.model.ActorType;
import com.khaledamin.ims.auth.security.core.jwt.JwtPayload;
import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import com.khaledamin.ims.auth.security.core.principal.PrincipalResolver;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountPrincipalResolver implements PrincipalResolver {

    private final AccountQueryService accountQueryService;

    @Override
    public ActorType getType() {
        return ActorType.ACCOUNT;
    }

    @Override
    @Transactional
    public AuthenticatedPrincipal resolve(JwtPayload payload) {

        Account account = accountQueryService.getOptionalByUsername(payload.getSubject())
                .orElseThrow(() -> CustomSecurityException.principalNotFound("Account not found")
                        .withDebugDetails("clientId", payload.getSubject())
                        .withDebugDetails("actorCode", payload.getActorCode())
                        .withDebugDetails("actorType", payload.getActorType().name()));

        return AccountPrincipal.of(
                payload.getSubject(),
                payload.getActorCode().toString(),

                account.getAccountStatus(),

                payload.getRoles(),
                payload.getAuthorities()
        );
    }
}