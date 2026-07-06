package com.khaledamin.ims.identity.client.domain.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientValidationError implements ValidationError {


    SUBJECT_INVALID(
            SystemDomain.IDENTITY,
            "CLIENT_SUBJECT_INVALID",
            "Client clientId is invalid"
    ),

    SECRET_INVALID(
            SystemDomain.IDENTITY,
            "CLIENT_SECRET_INVALID",
            "Client secret is invalid"
    ),

    SECRET_HASH_INVALID(
            SystemDomain.IDENTITY,
            "CLIENT_SECRET_HASH_INVALID",
            "Client secret hash is invalid"
    ),

    NAME_INVALID(
            SystemDomain.IDENTITY,
            "CLIENT_NAME_INVALID",
            "Client name is invalid"
    ),

    DESCRIPTION_INVALID(
            SystemDomain.IDENTITY,
            "CLIENT_DESCRIPTION_INVALID",
            "Client description is invalid"
    );

    private final SystemDomain domain;
    private final String code;
    private final String message;
}
