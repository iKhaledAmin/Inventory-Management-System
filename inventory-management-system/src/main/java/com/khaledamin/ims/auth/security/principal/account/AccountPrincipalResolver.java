package com.khaledamin.ims.auth.security.principal.account;

import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.repository.AccountRepository;
import com.khaledamin.ims.identity.core.model.ActorType;
import com.khaledamin.ims.auth.security.exception.AuthenticationException;
import com.khaledamin.ims.auth.security.jwt.JwtPayload;
import com.khaledamin.ims.auth.security.principal.core.AuthenticatedPrincipal;
import com.khaledamin.ims.auth.security.principal.core.PrincipalResolver;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountPrincipalResolver implements PrincipalResolver {

    private final AccountRepository accountRepository;

    @Override
    public ActorType getType() {
        return ActorType.ACCOUNT;
    }

    @Override
    @Transactional
    public AuthenticatedPrincipal resolve(JwtPayload payload) {

        Account account = accountRepository.findByUsername(payload.getSubject())
                .orElseThrow(() -> AuthenticationException.principalNotFound("Account not found")
                        .withDebugDetails("subject", payload.getSubject())
                        .withDebugDetails("actorCode", payload.getActorCode())
                        .withDebugDetails("actorType", payload.getActorType().name()));

        return AccountPrincipal.of(
                payload.getSubject(),
                payload.getActorCode().toString(),

                account.getAccountStatus(),

                payload.getRoles(),
                payload.getPermissions()
        );
    }
}