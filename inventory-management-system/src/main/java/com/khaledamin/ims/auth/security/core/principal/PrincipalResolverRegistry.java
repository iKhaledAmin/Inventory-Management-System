package com.khaledamin.ims.auth.security.core.principal;

import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import com.khaledamin.ims.auth.security.exception.SecurityTechnicalException;
import com.khaledamin.ims.auth.security.core.jwt.JwtPayload;
import com.khaledamin.ims.identity.core.model.ActorType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;


@Component
public class PrincipalResolverRegistry {

    private final Map<ActorType, PrincipalResolver> principalResolverMap;

    public PrincipalResolverRegistry(List<PrincipalResolver> resolvers) {

        Map<ActorType, PrincipalResolver> map = new EnumMap<>(ActorType.class);

        for (PrincipalResolver resolver : resolvers) {
            ActorType type = resolver.getType();

            if (map.containsKey(type)) {
                throw SecurityTechnicalException.duplicatePrincipalResolver(type);
            }

            map.put(type, resolver);
        }

        this.principalResolverMap = Map.copyOf(map);
    }


    public AuthenticatedPrincipal resolve(JwtPayload payload) {

        PrincipalResolver resolver = principalResolverMap.get(payload.getActorType());

        if (resolver == null) {
            throw SecurityTechnicalException.nullPrincipalResolver(payload.getActorType());
        }

        return resolver.resolve(payload);
    }
}