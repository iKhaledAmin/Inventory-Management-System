package com.khaledamin.ims.organization.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrganizationValidationError implements ValidationError {


    CODE_INVALID(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_CODE_INVALID",
            "Organization code is invalid"
    ),

    DESCRIPTION_INVALID(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_DESCRIPTION_INVALID",
            "Organization description is invalid"
    ),

    NAME_INVALID(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_NAME_INVALID",
            "Organization name is invalid"
    )

    ;
    private final SystemDomain domain;
    private final String code;
    private final String message;

}
