package com.khaledamin.ims.organization.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.business.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrganizationBusinessError implements BusinessError {

    NOT_FOUND(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_NOT_FOUND",
            HttpStatus.NOT_FOUND,
            "Organization not found"
    );
    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;
}
