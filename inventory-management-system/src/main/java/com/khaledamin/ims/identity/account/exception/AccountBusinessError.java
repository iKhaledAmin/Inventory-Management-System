package com.khaledamin.ims.identity.account.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.business.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AccountBusinessError implements BusinessError {

    NOT_FOUND(
            SystemDomain.ACCOUNT,
            "ACCOUNT_NOT_FOUND",
            HttpStatus.NOT_FOUND,
            "Account not found"
    ),

    ROLE_DUPLICATE(
            SystemDomain.ACCOUNT,
            "ACCOUNT_ROLE_DUPLICATE",
            HttpStatus.CONFLICT,
            "Account contains duplicate roles"
    ),

    ROLE_LIST_EMPTY(
            SystemDomain.ACCOUNT,
            "ACCOUNT_ROLE_LIST_MISSING",
            HttpStatus.CONFLICT,
            "Account role list must be non empty"
    ),

    IDENTITY_ROLE_MISSING(
            SystemDomain.ACCOUNT,
            "ACCOUNT_IDENTITY_ROLE_MISSING",
            HttpStatus.CONFLICT,
            "Account must have at least one identity role"
    ),


    PASSWORD_RESET_NOT_ALLOWED(
            SystemDomain.ACCOUNT,
            "ACCOUNT_PASSWORD_RESET_NOT_ALLOWED",
            HttpStatus.CONFLICT,
            "New password cannot be the same as the old password"
    ),

    ROLE_ASSIGN_NOT_ALLOWED(
            SystemDomain.ACCOUNT,
            "ACCOUNT_ROLE_ASSIGN_NOT_ALLOWED",
            HttpStatus.CONFLICT,
            "Role assignment not allowed"
    ),

    ROLE_REMOVE_NOT_ALLOWED(
            SystemDomain.ACCOUNT,
            "ACCOUNT_ROLE_REMOVE_NOT_ALLOWED",
            HttpStatus.CONFLICT,
            "Role removal not allowed"
    ),

    USERNAME_ALREADY_EXISTS(
            SystemDomain.ACCOUNT,
            "ACCOUNT_USERNAME_ALREADY_EXISTS",
            HttpStatus.CONFLICT,
            "Account username already exists"
    ),

    EMAIL_ALREADY_EXISTS(
            SystemDomain.ACCOUNT,
            "ACCOUNT_EMAIL_ALREADY_EXISTS",
            HttpStatus.CONFLICT,
            "Account email already exists"
    ),

    LAST_ADMIN_REMOVAL_NOT_ALLOWED(
            SystemDomain.ACCOUNT,
            "ACCOUNT_LAST_ADMIN_REMOVAL_NOT_ALLOWED",
            HttpStatus.CONFLICT,
            "Removing the last admin is not allowed"
    ),



    ;



    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;
}
