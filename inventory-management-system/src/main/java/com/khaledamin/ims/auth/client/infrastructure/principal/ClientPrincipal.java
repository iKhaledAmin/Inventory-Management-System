package com.khaledamin.ims.auth.client.infrastructure.principal;

import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import com.khaledamin.ims.identity.client.domain.model.ClientStatus;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ClientPrincipal implements AuthenticatedPrincipal {


    private final String clientId;

    private final String actorCode;

    private final ClientStatus status;

    private final Set<String> authorities;

    private final Set<GrantedAuthority> grantedAuthorities;



    public static ClientPrincipal of(String clientId, String actorCode, ClientStatus status, Set<String> authorities) {

        Set<String> immutableAuthorities = Set.copyOf(authorities);

        Set<GrantedAuthority> grantedAuthorities =
                immutableAuthorities
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet());

        return new ClientPrincipal(clientId, actorCode, status, immutableAuthorities, grantedAuthorities);
    }

    @Override
    public String getSubject() {
        return clientId;
    }

    @Override
    public ActorCode getActorCode() {
        return ActorCode.of(actorCode);
    }

    @Override
    public ActorType getActorType() {
        return ActorType.CLIENT;
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