package com.khaledamin.ims.identity.client.domain.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientTechnicalError implements TechnicalError {


    CAPABILITY_NULL(
            SystemDomain.IDENTITY,
            "CLIENT_CAPABILITY_NULL",
            "Capability is null"
    ),


    CLIENT_NULL(
            SystemDomain.IDENTITY,
            "CLIENT_NULL",
            "Client is null"
    ),

    CREATE_COMMAND_NULL(
            SystemDomain.IDENTITY,
            "CLIENT_CREATE_COMMAND_NULL",
            "Create command is null"
    ),

    UPDATE_COMMAND_NULL(
            SystemDomain.IDENTITY,
            "CLIENT_UPDATE_COMMAND_NULL",
            "Update command is null"
    ),

    HASH_SECRET_NULL(
            SystemDomain.IDENTITY,
            "CLIENT_HASH_SECRET_NULL",
            "Hash secret is null"
    );

    private final SystemDomain domain;
    private final String code;
    private final String message;
}
