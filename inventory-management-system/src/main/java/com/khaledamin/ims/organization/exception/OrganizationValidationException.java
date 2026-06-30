package com.khaledamin.ims.organization.exception;

import com.khaledamin.ims.core.exception.validation.ValidationError;
import com.khaledamin.ims.core.exception.validation.ValidationException;

public class OrganizationValidationException extends ValidationException {
    // -------------------------------------------- Constructors -------------------------------------------- //

    protected OrganizationValidationException(ValidationError error) {
        super(error);
    }


    // -------------------------------------------- Static Methods -------------------------------------------- //

    public static OrganizationValidationException invalidCode() {
        return new OrganizationValidationException(OrganizationValidationError.CODE_INVALID);
    }

    public static OrganizationValidationException invalidDescription() {
        return new OrganizationValidationException(OrganizationValidationError.DESCRIPTION_INVALID);
    }

    public static OrganizationValidationException invalidName() {
        return new OrganizationValidationException(OrganizationValidationError.NAME_INVALID);
    }

}
