package com.khaledamin.ims.identity.capability.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.business.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CapabilityBusinessError implements BusinessError {

    NOT_FOUND(
            SystemDomain.CAPABILITY,
            "CAPABILITY_NOT_FOUND",
            HttpStatus.NOT_FOUND,
            "Capability not found"
    ),


    ;

    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;


}