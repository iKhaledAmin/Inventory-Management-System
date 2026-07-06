package com.khaledamin.ims.auth.client.infrastructure.jwt;

import com.khaledamin.ims.auth.client.infrastructure.principal.ClientPrincipal;
import com.khaledamin.ims.auth.security.core.jwt.JwtClaims;
import com.khaledamin.ims.auth.security.core.jwt.JwtClaimsContributor;
import io.jsonwebtoken.JwtBuilder;
import org.springframework.stereotype.Component;

@Component
public class ClientJwtClaimsContributor
        implements JwtClaimsContributor<ClientPrincipal> {


    @Override
    public Class<ClientPrincipal> getSupportedPrincipal() {
        return ClientPrincipal.class;
    }

    @Override
    public void contribute(JwtBuilder builder, ClientPrincipal principal) {

        builder.claim(JwtClaims.AUTHORITIES, principal.getAuthorities());

    }
}