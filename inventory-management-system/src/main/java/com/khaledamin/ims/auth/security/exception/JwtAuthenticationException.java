package com.khaledamin.ims.auth.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}