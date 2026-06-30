package com.khaledamin.ims.identity.role.exception;

import com.khaledamin.ims.core.exception.validation.ValidationException;
import com.khaledamin.ims.core.exception.validation.ValidationError;

public class RoleValidationException extends ValidationException {

    // ----------------------------------- Constructors ----------------------------------- //

    protected RoleValidationException(ValidationError error) {
        super(error);
    }


    // ----------------------------------- Factories ----------------------------------- //


    public static RoleValidationException invalidName() {
        return new RoleValidationException(RoleValidationError.NAME_INVALID);
    }

    public static RoleValidationException invalidDisplayName() {
        return new RoleValidationException(RoleValidationError.DISPLAY_NAME_INVALID);
    }

    public static RoleValidationException invalidDescription() {
        return new RoleValidationException(RoleValidationError.DESCRIPTION_INVALID);
    }

}