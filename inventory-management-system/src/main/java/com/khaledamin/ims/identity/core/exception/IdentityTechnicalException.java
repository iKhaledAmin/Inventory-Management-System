package com.khaledamin.ims.identity.core.exception;


import com.khaledamin.ims.core.exception.technical.TechnicalError;
import com.khaledamin.ims.core.exception.technical.TechnicalException;
import com.khaledamin.ims.core.exception.validation.ValidationException;
import com.khaledamin.ims.identity.core.model.ActorType;

public class IdentityTechnicalException extends TechnicalException {

    // ----------------------------------- Constructors ----------------------------------- //

    protected IdentityTechnicalException(TechnicalError error) {
        super(error);
    }

    protected IdentityTechnicalException(TechnicalError error, Throwable cause) {
        super(error, cause);
    }

    protected IdentityTechnicalException(TechnicalError error, String message) {
        super(error, message);
    }

    // ----------------------------------- Factories ----------------------------------- //

    public static IdentityTechnicalException invalid() {
        return new IdentityTechnicalException(IdentityTechnicalError.INVALID);
    }

    public static IdentityTechnicalException invalidGeneratedActorCode(ValidationException e) {
        return new IdentityTechnicalException(IdentityTechnicalError.GENERATED_ACTOR_CODE_INVALID, e);
    }


    public static IdentityTechnicalException sourceTypeMismatch(Class<?> expected, Class<?> actual) {

        return new IdentityTechnicalException(
                IdentityTechnicalError.ACTOR_SOURCE_TYPE_MISMATCH,
                "ActorSource type mismatch: expected "
                        + expected.getName()
                        + " but got "
                        + actual.getName()
        );
    }

    public static IdentityTechnicalException principalTypeMismatch(Class<?> expected, Class<?> actual) {

        return new IdentityTechnicalException(
                IdentityTechnicalError.ACTOR_PRINCIPAL_TYPE_MISMATCH,
                "Principal type mismatch: expected "
                        + expected.getName()
                        + " but got "
                        + actual.getName()
        );
    }

    public static IdentityTechnicalException resolverNotFound(ActorType type) {

        return new IdentityTechnicalException(
                IdentityTechnicalError.ACTOR_RESOLVER_NOT_FOUND,
                "No resolver registered for ActorType: " + type
        );
    }

    public static IdentityTechnicalException registryConflict(ActorType type) {

        return new IdentityTechnicalException(
                IdentityTechnicalError.ACTOR_REGISTRY_CONFLICT,
                "Duplicate resolver registration for ActorType: " + type
        );
    }
}
