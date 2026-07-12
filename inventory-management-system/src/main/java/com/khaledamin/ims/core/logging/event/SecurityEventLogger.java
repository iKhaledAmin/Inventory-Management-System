package com.khaledamin.ims.core.logging.event;

import com.khaledamin.ims.auth.security.core.authentication.AuthenticatedPrincipal;
import com.khaledamin.ims.auth.security.exception.CustomSecurityException;


public interface SecurityEventLogger {

    void authenticationSucceeded(AuthenticatedPrincipal principal);
    void authenticationFailed(CustomSecurityException ex);

    void authorizationDenied(String method, String path , String message);

    void loginSucceeded(AuthenticatedPrincipal principal);
    void loginFailed(String username, CustomSecurityException ex);







}