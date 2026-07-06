package com.khaledamin.ims.auth.account.infrastructure.jwt;

import com.khaledamin.ims.auth.account.infrastructure.principal.AccountPrincipal;
import com.khaledamin.ims.auth.security.core.jwt.JwtClaims;
import com.khaledamin.ims.auth.security.core.jwt.JwtClaimsContributor;
import io.jsonwebtoken.JwtBuilder;
import org.springframework.stereotype.Component;

@Component
public class AccountJwtClaimsContributor
        implements JwtClaimsContributor<AccountPrincipal> {


    @Override
    public Class<AccountPrincipal> getSupportedPrincipal() {
        return AccountPrincipal.class;
    }

    @Override
    public void contribute(JwtBuilder builder, AccountPrincipal principal) {

        builder.claim(JwtClaims.ROLES, principal.getRoles());

        builder.claim(JwtClaims.AUTHORITIES, principal.getAuthorities());
    }
}