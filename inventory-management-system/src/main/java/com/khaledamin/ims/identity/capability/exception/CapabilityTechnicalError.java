package com.khaledamin.ims.identity.capability.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CapabilityTechnicalError implements TechnicalError {


    CREATE_COMMAND_NULL(
            SystemDomain.CAPABILITY,
            "CAPABILITY_CREATE_COMMAND_NULL",
            "Capability create command is null"
    ),

    CREATE_UPDATE_NULL(
            SystemDomain.CAPABILITY,
            "CAPABILITY_CREATE_UPDATE_NULL",
            "Capability create or update command is null"
    ),

    CODE_DUPLICATE(
            SystemDomain.CAPABILITY,
            "CAPABILITY_CODE_DUPLICATE",
            "Capability code is duplicate"
    ),

    PROVIDER_NULL(
            SystemDomain.CAPABILITY,
            "CAPABILITY_PROVIDER_NULL",
            "Capability provider is null"
    ),

;

    private final SystemDomain domain;
    private final String code;
    private final String message;
}