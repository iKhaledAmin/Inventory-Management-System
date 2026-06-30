package com.khaledamin.ims.identity.capability.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalError;
import com.khaledamin.ims.core.exception.technical.TechnicalException;

public class CapabilityTechnicalException extends TechnicalException {

    // ----------------------------------- Constructors ----------------------------------- //

    private CapabilityTechnicalException(TechnicalError error) {
        super(error);
    }


    // ----------------------------------- Factories ----------------------------------- //

    public static CapabilityTechnicalException nullCreateCommand() {
        return new CapabilityTechnicalException(CapabilityTechnicalError.CREATE_COMMAND_NULL);
    }

    public static CapabilityTechnicalException nullUpdateCommand() {
        return new CapabilityTechnicalException(CapabilityTechnicalError.CREATE_UPDATE_NULL);
    }

    public static CapabilityTechnicalException nullProvider() {
        return new CapabilityTechnicalException(CapabilityTechnicalError.PROVIDER_NULL);
    }

    public static CapabilityTechnicalException duplicateCode() {
        return new CapabilityTechnicalException(CapabilityTechnicalError.CODE_DUPLICATE);
    }


}