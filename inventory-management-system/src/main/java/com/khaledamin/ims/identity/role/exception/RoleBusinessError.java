package com.khaledamin.ims.identity.role.exception;


import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.business.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RoleBusinessError implements BusinessError {

    NOT_FOUND(
            SystemDomain.ROLE,
            "ROLE_NOT_FOUND",
            HttpStatus.NOT_FOUND,
            "Role not found"
    ),

    SOME_NOT_FOUND(
            SystemDomain.ROLE,
            "ROLE_SOME_ROLES_NOT_FOUND",
            HttpStatus.NOT_FOUND,
            "Some requested roles were not found"
    ),

    ROLE_ASSIGNED_TO_ACCOUNTS(
            SystemDomain.ROLE,
            "ROLE_ASSIGNED_TO_ACCOUNTS",
            HttpStatus.CONFLICT,
            "Role is assigned to one or more accounts"
    ),


    NAME_ALREADY_EXISTS(
            SystemDomain.ROLE,
            "ROLE_NAME_ALREADY_EXISTS",
            HttpStatus.CONFLICT,
            "Role name already exists"
    ),

    DISPLAY_NAME_ALREADY_EXISTS(
            SystemDomain.ROLE,
            "ROLE_DISPLAY_NAME_ALREADY_EXISTS",
            HttpStatus.CONFLICT,
            "Role display name already exists"
    ),


    CAPABILITY_ALREADY_ASSIGNED(
            SystemDomain.ROLE,
            "ROLE_CAPABILITY_ALREADY_ASSIGNED",
            HttpStatus.CONFLICT,
            "Capability already assigned to role"
    ),

    CAPABILITY_NOT_ASSIGNED(
            SystemDomain.ROLE,
            "ROLE_CAPABILITY_NOT_ASSIGNED",
            HttpStatus.CONFLICT,
            "Capability is not assigned to role"
    ),

    ;

    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;
}