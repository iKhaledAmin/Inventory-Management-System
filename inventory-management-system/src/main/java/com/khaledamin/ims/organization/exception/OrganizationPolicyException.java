package com.khaledamin.ims.organization.exception;

import com.khaledamin.ims.core.exception.policy.PolicyError;
import com.khaledamin.ims.core.exception.policy.PolicyException;

public class OrganizationPolicyException extends PolicyException {
    // ---------------------------------- Constructors ---------------------------------- //
    protected OrganizationPolicyException(PolicyError error) {
        super(error);
    }
    // -------------------------------- End Constructors -------------------------------- //

    // ---------------------------------- Methods ---------------------------------- //


    public static OrganizationPolicyException forbiddenRotateClientSecret() {
        return new OrganizationPolicyException(OrganizationPolicyError.ROTATE_CLIENT_SECRET_FORBIDDEN);
    }

    // -------------------------------- End Methods -------------------------------- //
}
