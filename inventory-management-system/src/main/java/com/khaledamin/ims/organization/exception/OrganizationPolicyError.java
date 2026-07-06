package com.khaledamin.ims.organization.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.policy.PolicyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrganizationPolicyError implements PolicyError {


    ROTATE_CLIENT_SECRET_FORBIDDEN(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_ROTATE_CLIENT_SECRET_FORBIDDEN",
            "Rotate client secret is forbidden"
    );
    private final SystemDomain domain;
    private final String code;
    private final String message;
}
