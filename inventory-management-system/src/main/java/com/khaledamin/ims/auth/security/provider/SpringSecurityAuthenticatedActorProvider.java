package com.khaledamin.ims.auth.security.provider;


import com.khaledamin.ims.auth.security.exception.SecurityTechnicalException;
import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.AnonymousActor;
import com.khaledamin.ims.identity.core.model.SystemActor;
import com.khaledamin.ims.identity.core.provider.AuthenticatedActorProvider;
import com.khaledamin.ims.identity.core.registry.ActorPrincipalResolverRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SpringSecurityAuthenticatedActorProvider implements AuthenticatedActorProvider {

    private final ActorPrincipalResolverRegistry actorPrincipalResolverRegistry;

    @Override
    public Actor getCurrentActor() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //  No authentication → system context (batch / startup operations / scheduled jobs / background processing / etc.)
        if (auth == null) {
            return SystemActor.INSTANCE;
        }

        //  Anonymous request (not logged in) → anonymous context (e.g. public API [register, login, etc.])
        if (auth instanceof AnonymousAuthenticationToken) {
            return AnonymousActor.INSTANCE;
        }

        //  Not authenticated [Authentication exists but not valid] → fallback to anonymous context
        if (!auth.isAuthenticated()) {
            return AnonymousActor.INSTANCE;
        }

        Object principal = auth.getPrincipal();

        //  Valid authenticated principal → actor  (resolve via registry)
        if (principal instanceof AuthenticatedPrincipal authenticatedPrincipal) {
            return actorPrincipalResolverRegistry.resolve(authenticatedPrincipal);
        }


        // Unknown → fallback
        throw SecurityTechnicalException.unsupportedPrincipalType(principal.getClass())
                .withDebugDetails("principalType", principal.getClass().getName())
                .withDebugDetails("authenticationType", auth.getClass().getName())
                .withDebugDetails("authenticated", auth.isAuthenticated())
                .withDebugDetails("authenticationName", auth.getName());

    }

}
