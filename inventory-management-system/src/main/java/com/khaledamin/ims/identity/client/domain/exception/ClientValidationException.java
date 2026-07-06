package com.khaledamin.ims.identity.client.domain.exception;

import com.khaledamin.ims.core.exception.validation.ValidationError;
import com.khaledamin.ims.core.exception.validation.ValidationException;

public class ClientValidationException extends ValidationException {
    // ----------------------------------------- Constructors ----------------------------------------- //
    protected ClientValidationException(ValidationError error) {
        super(error);
    }
    // ---------------------------------------- End Constructors ---------------------------------------- //


    // ----------------------------------------- Static Methods ----------------------------------------- //

    public static ClientValidationException invalidId() {
        return new ClientValidationException(ClientValidationError.SUBJECT_INVALID);
    }

    public static ClientValidationException invalidSecret() {
        return new ClientValidationException(ClientValidationError.SECRET_INVALID);
    }

    public static ClientValidationException invalidSecretHash() {
        return new ClientValidationException(ClientValidationError.SECRET_HASH_INVALID);
    }

    public static ClientValidationException invalidName() {
        return new ClientValidationException(ClientValidationError.NAME_INVALID);
    }

    public static ClientValidationException invalidDescription() {
        return new ClientValidationException(ClientValidationError.DESCRIPTION_INVALID);
    }

    // ---------------------------------------- End Static Methods ---------------------------------------- //
}
