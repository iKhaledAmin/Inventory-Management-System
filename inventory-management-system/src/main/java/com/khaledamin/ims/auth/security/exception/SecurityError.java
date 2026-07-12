package com.khaledamin.ims.auth.security.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.core.BaseError;
import com.khaledamin.ims.core.exception.core.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SecurityError implements BaseError {

    INVALID_CREDENTIALS(
            SystemDomain.SECURITY,
            "SECURITY_CREDENTIALS_INVALID",
            HttpStatus.UNAUTHORIZED,
            "Invalid credentials"
    ),

    AUTHENTICATION_FAILED(
            SystemDomain.SECURITY,
            "SECURITY_AUTHENTICATION_FAILED",
            HttpStatus.UNAUTHORIZED,
            "Authentication failed"
    ),

    TOKEN_INVALID(
            SystemDomain.SECURITY,
            "SECURITY_TOKEN_INVALID",
            HttpStatus.UNAUTHORIZED,
            "Token invalid"
    ),

    TOKEN_EXPIRED(
            SystemDomain.SECURITY,
            "SECURITY_TOKEN_EXPIRED",
            HttpStatus.UNAUTHORIZED,
            "Token expired"
    ),

    PRINCIPAL_NOT_FOUND(
            SystemDomain.SECURITY,
            "SECURITY_PRINCIPAL_NOT_FOUND",
            HttpStatus.UNAUTHORIZED,
            "Principal not found"
    ),

    PRINCIPAL_LOCKED(
            SystemDomain.SECURITY,
            "SECURITY_PRINCIPAL_LOCKED",
            HttpStatus.UNAUTHORIZED,
            "Principal is locked"
    ),

    PRINCIPAL_INACTIVE(
            SystemDomain.SECURITY,
            "SECURITY_PRINCIPAL_INACTIVE",
            HttpStatus.UNAUTHORIZED,
            "Principal is inactive"
    ),

    ACCESS_DENIED(
            SystemDomain.SECURITY,
            "SECURITY_ACCESS_DENIED",
            HttpStatus.FORBIDDEN,
            "Access denied"
    );

    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;

    @Override
    public ErrorType getType() {
        return ErrorType.SECURITY;
    }
}