package com.khaledamin.ims.auth.security.provider;

import com.khaledamin.ims.auth.security.principal.core.AuthenticatedPrincipal;

public interface CredentialAuthenticationService <P extends AuthenticatedPrincipal> {

    P authenticate(String subject, String credential);
}