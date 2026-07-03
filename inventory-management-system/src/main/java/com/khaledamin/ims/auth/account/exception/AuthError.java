package com.khaledamin.ims.auth.account.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.business.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthError implements BusinessError {

    RESET_PASSWORD_FAILED(
            SystemDomain.AUTH,
            "AUTH_RESET_PASSWORD_FAILED",
            HttpStatus.BAD_REQUEST,
            "Reset password failed"
    ),


    ACTIVATION_FAILED(
            SystemDomain.AUTH,
            "AUTH_ACTIVATION_FAILED",
            HttpStatus.BAD_REQUEST,
            "Activation failed"
    );

    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;
}
