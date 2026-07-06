package com.khaledamin.ims.identity.client.domain.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalError;
import com.khaledamin.ims.core.exception.technical.TechnicalException;

public class ClientTechnicalException extends TechnicalException {
    // --------------------------------- Constructors --------------------------------- //
    protected ClientTechnicalException(TechnicalError error) {
        super(error);
    }

    // --------------------------------- End Constructors ----------------------------- //


    // --------------------------------- Business Methods ----------------------------- //

    public static ClientTechnicalException nullCapability() {
        return new ClientTechnicalException(ClientTechnicalError.CAPABILITY_NULL);
    }

    public static ClientTechnicalException nullClient() {
        return new ClientTechnicalException(ClientTechnicalError.CLIENT_NULL);
    }

    public static ClientTechnicalException nullCreateCommand() {
        return new ClientTechnicalException(ClientTechnicalError.CREATE_COMMAND_NULL);
    }

    public static ClientTechnicalException nullUpdateCommand() {
        return new ClientTechnicalException(ClientTechnicalError.UPDATE_COMMAND_NULL);
    }

    public static ClientTechnicalException nullHashSecret() {
        return new ClientTechnicalException(ClientTechnicalError.HASH_SECRET_NULL);
    }

    // --------------------------------- End Business Methods ------------------------- //
}
