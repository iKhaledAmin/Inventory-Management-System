package com.khaledamin.ims.identity.core.exception;


import com.khaledamin.ims.core.exception.validation.ValidationError;
import com.khaledamin.ims.core.exception.validation.ValidationException;

public class IdentityValidationException extends ValidationException {
    // ----------------------------------- Constructors ----------------------------------- //
    protected IdentityValidationException(ValidationError error) {
        super(error);
    }

    protected IdentityValidationException(ValidationError error, Throwable cause) {
        super(error, cause);
    }

    // ----------------------------------- Factories ----------------------------------- //

    public static IdentityValidationException invalidActorCode() {
        return new IdentityValidationException(IdentityValidationError.ACTOR_CODE_INVALID);
    }

    public static IdentityValidationException invalidActorType() {
        return new IdentityValidationException(IdentityValidationError.ACTOR_TYPE_INVALID);
    }

    public static IdentityValidationException invalidActorType(Throwable cause) {
        return new IdentityValidationException(IdentityValidationError.ACTOR_TYPE_INVALID, cause);
    }
}
