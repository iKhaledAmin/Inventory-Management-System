package com.khaledamin.ims.auth.security.core.jwt;

import com.khaledamin.ims.auth.security.exception.SecurityTechnicalException;
import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtClaimsContributorRegistry {

    private final Map<Class<?>, JwtClaimsContributor<?>> contributors;

    public JwtClaimsContributorRegistry(List<JwtClaimsContributor<?>> contributors) {

        this.contributors = contributors
                .stream()
                .collect(Collectors.toUnmodifiableMap(
                        JwtClaimsContributor::getSupportedPrincipal,
                        Function.identity()
                ));
    }

    @SuppressWarnings("unchecked")
    public <T extends AuthenticatedPrincipal> JwtClaimsContributor<T> get(T principal) {

        JwtClaimsContributor<?> contributor = contributors.get(principal.getClass());
        if (contributor == null) {
            throw SecurityTechnicalException.nullJwtClaimsContributor(principal.getActorType());
        }

        return (JwtClaimsContributor<T>) contributor;
    }
}