package com.khaledamin.ims.identity.role.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleTechnicalError implements TechnicalError {

    ROLE_NULL(
            SystemDomain.ROLE,
            "ROLE_NULL",
            "Role is null"
    ),

    ROLE_DEFINITION_NULL(
            SystemDomain.ROLE,
            "ROLE_DEFINITION_NULL",
            "Role definition is null"
    ),

    CREATE_COMMAND_NULL(
            SystemDomain.ROLE,
            "ROLE_CREATE_COMMAND_NULL",
            "Role create command is null"
    ),

    UPDATE_COMMAND_NULL(
            SystemDomain.ROLE,
            "ROLE_UPDATE_COMMAND_NULL",
            "Role update command is null"
    ),

    CAPABILITY_NULL(
            SystemDomain.ROLE,
            "ROLE_CAPABILITY_NULL",
            "Capability to be assigned is null"
    ),

    DEFAULT_ROLE_NOT_CONFIGURED(
            SystemDomain.ROLE,
            "ROLE_DEFAULT_ROLE_NOT_CONFIGURED",
            "At least one default role must be configured"
    );
    private final SystemDomain domain;
    private final String code;
    private final String message;
}
