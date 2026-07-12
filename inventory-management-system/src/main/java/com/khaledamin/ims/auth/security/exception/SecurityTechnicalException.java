package com.khaledamin.ims.auth.security.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalException;
import com.khaledamin.ims.core.exception.technical.TechnicalError;
import com.khaledamin.ims.identity.core.model.ActorType;

public class SecurityTechnicalException extends TechnicalException {

    // ----------------------------------- Constructors ----------------------------------- //

    protected SecurityTechnicalException(TechnicalError error, String message) {
        super(error, message);
    }

    // -------------------------------------- Factories ------------------------------------- //

    public static SecurityTechnicalException invalidConfiguration(String message) {
        return new SecurityTechnicalException(
                SecurityTechnicalError.CONFIGURATION_INVALID,
                message
        );
    }

    public static SecurityTechnicalException duplicatePrincipalResolver(ActorType type) {

        return new SecurityTechnicalException(
                SecurityTechnicalError.PRINCIPAL_RESOLVER_DUPLICATE,
                "Duplicate PrincipalResolver registered for ActorType: " + type
        );
    }

    public static SecurityTechnicalException nullPrincipalResolver(ActorType type) {

        return new SecurityTechnicalException(
                SecurityTechnicalError.PRINCIPAL_RESOLVER_NULL,
                "No PrincipalResolver registered for ActorType: " + type
        );
    }

    public static SecurityTechnicalException unsupportedPrincipalType(Class<?> principalType) {

        return new SecurityTechnicalException(
                SecurityTechnicalError.UNSUPPORTED_PRINCIPAL_TYPE,
                "Unsupported authenticated principal type: " + principalType.getName()
        );
    }

    public static SecurityTechnicalException nullJwtClaimsContributor(ActorType type) {

        return new SecurityTechnicalException(
                SecurityTechnicalError.JWT_CLAIMS_CONTRIBUTOR_NULL,
                "No JwtClaimsContributor registered for principal type: "
                        + type.name()
        );
    }
}