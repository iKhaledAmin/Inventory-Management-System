package com.khaledamin.ims.identity.core.provider;


import com.khaledamin.ims.auth.security.principal.core.AuthenticatedPrincipal;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorSource;
import com.khaledamin.ims.identity.core.registry.ActorPrincipalResolverRegistry;
import com.khaledamin.ims.identity.core.registry.ActorSourceResolverRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActorProviderImpl implements ActorProvider {
    private final AuthenticatedActorProvider authenticatedActorProvider;
    private final ActorPrincipalResolverRegistry principalResolverRegistry;
    private final ActorSourceResolverRegistry sourceResolverRegistry;

    @Override
    public Actor getCurrent() {
        return authenticatedActorProvider.getCurrentActor();
    }

    @Override
    public Actor getFrom(ActorSource source) {
        return sourceResolverRegistry.resolve(source);
    }

    @Override
    public Actor getFrom(AuthenticatedPrincipal principal) {
        return principalResolverRegistry.resolve(principal);
    }


}
