package com.khaledamin.ims.organization.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalError;
import com.khaledamin.ims.core.exception.technical.TechnicalException;

public class OrganizationTechnicalException extends TechnicalException {
    // -------------------------------------------- Constructors -------------------------------------------- //

    protected OrganizationTechnicalException(TechnicalError error) {
        super(error);
    }


    // -------------------------------------------- Static Methods -------------------------------------------- //

    public static OrganizationTechnicalException nullCreateCommand() {
        return new OrganizationTechnicalException(OrganizationTechnicalError.CREATE_COMMAND_NULL);
    }

    public static OrganizationTechnicalException nullUpdateCommand() {
        return new OrganizationTechnicalException(OrganizationTechnicalError.UPDATE_COMMAND_NULL);
    }

    public static OrganizationTechnicalException nullOwnerAccount() {
        return new OrganizationTechnicalException(OrganizationTechnicalError.OWNER_ACCOUNT_NULL);
    }

    public static OrganizationTechnicalException nullImage() {
        return new OrganizationTechnicalException(OrganizationTechnicalError.IMAGE_NULL);
    }

    public static OrganizationTechnicalException nullMemberIdentity() {
        return new OrganizationTechnicalException(OrganizationTechnicalError.MEMBER_IDENTITY_NULL);
    }

    public static OrganizationTechnicalException imageUploadFailed() {
        return new OrganizationTechnicalException(OrganizationTechnicalError.IMAGE_UPLOAD_FAILED);
    }
}
