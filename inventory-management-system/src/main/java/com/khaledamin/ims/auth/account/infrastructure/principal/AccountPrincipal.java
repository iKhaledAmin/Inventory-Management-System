package com.khaledamin.ims.auth.account.infrastructure.principal;

import com.khaledamin.ims.identity.account.domain.model.AccountStatus;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorType;
import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@RequiredArgsConstructor
public class AccountPrincipal implements AuthenticatedPrincipal {

    private final String username;
    private final String actorCode;

    private final AccountStatus status;

    private final Set<String> roles;
    private final Set<String> authorities;

    private final Set<GrantedAuthority> grantedAuthorities;


    public static AccountPrincipal of(
            String username,
            String actorCode,
            AccountStatus status,
            Set<String> roles,
            Set<String> authorities
    ) {

        Set<String> immutableRoles = Set.copyOf(roles);

        Set<String> immutableAuthorities = Set.copyOf(authorities);

        Set<GrantedAuthority> grantedAuthorities =
                Stream.concat(
                                immutableRoles
                                        .stream()
                                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role)),

                                immutableAuthorities
                                        .stream()
                                        .map(SimpleGrantedAuthority::new)

                        ).collect(Collectors.toUnmodifiableSet());

        return new AccountPrincipal(
                username,
                actorCode,
                status,
                immutableRoles,
                immutableAuthorities,
                grantedAuthorities
        );
    }

    @Override
    public String getSubject() {
        return username;
    }

    @Override
    public ActorCode getActorCode() {
        return ActorCode.of(actorCode);
    }

    @Override
    public ActorType getActorType() {
        return ActorType.ACCOUNT;
    }

    @Override
    public boolean isActive() {
        return status.isActive();
    }

    @Override
    public boolean isLocked() {
        return status.isLocked();
    }

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }
}

