package com.khaledamin.ims.auth.security.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.security.SecurityError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthenticationError implements SecurityError {

    // ================= CREDENTIALS =================

    CREDENTIALS_INVALID(
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

    // ================= PRINCIPAL =================

    PRINCIPAL_NOT_FOUND(
            SystemDomain.SECURITY,
            "SECURITY_PRINCIPAL_NOT_FOUND",
            HttpStatus.UNAUTHORIZED,
            "Principal not found"
    ),

    // ================= TOKEN =================

    TOKEN_INVALID(
            SystemDomain.SECURITY,
            "SECURITY_TOKEN_INVALID",
            HttpStatus.UNAUTHORIZED,
            "Invalid token"
    ),

    TOKEN_EXPIRED(
            SystemDomain.SECURITY,
            "SECURITY_TOKEN_EXPIRED",
            HttpStatus.UNAUTHORIZED,
            "Token expired"
    ),

    TOKEN_MALFORMED(
            SystemDomain.SECURITY,
            "SECURITY_TOKEN_MALFORMED",
            HttpStatus.UNAUTHORIZED,
            "Malformed token"
    ),

    TOKEN_SIGNATURE_INVALID(
            SystemDomain.SECURITY,
            "SECURITY_TOKEN_SIGNATURE_INVALID",
            HttpStatus.UNAUTHORIZED,
            "Invalid token signature"
    ),

    TOKEN_MISSING(
            SystemDomain.SECURITY,
            "SECURITY_TOKEN_MISSING",
            HttpStatus.UNAUTHORIZED,
            "Authentication token is missing"
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
    );


    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;
}