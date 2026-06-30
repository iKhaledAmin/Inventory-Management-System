package com.khaledamin.ims.identity.role.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalException;
import com.khaledamin.ims.core.exception.technical.TechnicalError;

public class RoleTechnicalException extends TechnicalException {

    // ----------------------------------- Constructors ----------------------------------- //
    protected RoleTechnicalException(TechnicalError error) {
        super(error);
    }

    // ----------------------------------- Factories ----------------------------------- //

    public static RoleTechnicalException nullRole() {
        return new RoleTechnicalException(RoleTechnicalError.ROLE_NULL);
    }

    public static RoleTechnicalException nullRoleDefinition() {
        return new RoleTechnicalException(RoleTechnicalError.ROLE_DEFINITION_NULL);
    }

    public static RoleTechnicalException nullCreateCommand() {
        return new RoleTechnicalException(RoleTechnicalError.CREATE_COMMAND_NULL);
    }

    public static RoleTechnicalException nullUpdateCommand() {
        return new RoleTechnicalException(RoleTechnicalError.UPDATE_COMMAND_NULL);
    }

    public static RoleTechnicalException nullCapability() {
        return new RoleTechnicalException(RoleTechnicalError.CAPABILITY_NULL);
    }

    public static RoleTechnicalException defaultRoleNotConfigured() {
        return new RoleTechnicalException(RoleTechnicalError.DEFAULT_ROLE_NOT_CONFIGURED);
    }



}
