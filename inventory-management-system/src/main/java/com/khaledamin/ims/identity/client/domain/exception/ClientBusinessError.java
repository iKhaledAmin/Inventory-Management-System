package com.khaledamin.ims.identity.client.domain.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.business.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ClientBusinessError implements BusinessError {


    CAPABILITY_ALREADY_ASSIGNED(
            SystemDomain.IDENTITY,
            "CLIENT_CAPABILITY_ALREADY_ASSIGNED",
            HttpStatus.BAD_REQUEST,
            "Capability already assigned to client"
    ),


    CAPABILITY_NOT_ASSIGNED(
            SystemDomain.IDENTITY,
            "CLIENT_CAPABILITY_NOT_ASSIGNED",
            HttpStatus.BAD_REQUEST,
            "Capability not assigned to client"
    ),

    NOT_FOUND(
            SystemDomain.IDENTITY,
            "CLIENT_NOT_FOUND",
            HttpStatus.NOT_FOUND,
            "Client not found"
    ),

    CLIENT_ID_ALREADY_EXISTS(
            SystemDomain.IDENTITY,
            "CLIENT_ID_ALREADY_EXISTS",
            HttpStatus.BAD_REQUEST,
            "Client ID already exists"
    );



    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;
}
