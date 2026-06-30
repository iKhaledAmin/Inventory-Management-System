package com.khaledamin.ims.identity.role.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleValidationError implements ValidationError {

    NAME_INVALID(
            SystemDomain.ROLE,
            "ROLE_NAME_INVALID",
            "Role name is invalid"
    ),

    DISPLAY_NAME_INVALID(
            SystemDomain.ROLE,
            "ROLE_DISPLAY_NAME_INVALID",
            "Role display name is invalid"
    ),

    DESCRIPTION_INVALID(
            SystemDomain.ROLE,
            "ROLE_DESCRIPTION_INVALID",
            "Role description is invalid"
    ),




    ;

    private final SystemDomain domain;
    private final String code;
    private final String message;
}