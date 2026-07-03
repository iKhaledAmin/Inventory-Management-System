package com.khaledamin.ims.auth.security.exception;

import com.khaledamin.ims.core.exception.security.SecurityException;
import com.khaledamin.ims.core.exception.security.SecurityError;

public class AuthorizationException extends SecurityException {

    // --------------------------------------------- Constructors --------------------------------------------- //

    protected AuthorizationException(SecurityError error) {
        super(error);
    }

    protected AuthorizationException(SecurityError error, Throwable cause) {
        super(error, cause);
    }

    protected AuthorizationException(SecurityError error, String message) {
        super(error, message);
    }

    protected AuthorizationException(SecurityError error,
                                     String message,
                                     Throwable cause) {
        super(error, message, cause);
    }

    // --------------------------------------------- Factories --------------------------------------------- //

    // ================= ACCESS =================

    public static AuthorizationException accessDenied() {
        return new AuthorizationException(AuthorizationError.ACCESS_DENIED);
    }

    public static AuthorizationException insufficientScope() {
        return new AuthorizationException(AuthorizationError.INSUFFICIENT_SCOPE);
    }


}