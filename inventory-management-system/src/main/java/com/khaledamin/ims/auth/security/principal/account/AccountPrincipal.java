package com.khaledamin.ims.auth.security.principal.account;

import com.khaledamin.ims.identity.account.domain.model.AccountStatus;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorType;
import com.khaledamin.ims.auth.security.principal.core.AuthenticatedPrincipal;
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
    private final String accountCode;

    private final AccountStatus accountStatus;

    private final Set<String> roles;
    private final Set<String> permissions;

    private final Set<GrantedAuthority> authorities;




    public static AccountPrincipal of(
            String username, String accountCode,
            AccountStatus accountStatus,
            Set<String> roles, Set<String> permissions
    ) {
        Set<GrantedAuthority> authorities = Stream.concat(
                        // Roles
                        roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)),

                        // Permissions / Capabilities
                        permissions.stream().map(SimpleGrantedAuthority::new)
                )
                .collect(Collectors.toUnmodifiableSet());

        return new AccountPrincipal(username, accountCode, accountStatus, roles, permissions, authorities);
    }


    @Override
    public String getSubject() {
        return username;
    }

    @Override
    public ActorCode getActorCode() {
        return ActorCode.of(accountCode);
    }

    @Override
    public ActorType getActorType() {
        return ActorType.ACCOUNT;
    }


    @Override
    public boolean isActive() {
        return accountStatus.isActive();
    }

    @Override
    public boolean isLocked() {
        return accountStatus.isLocked();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}

