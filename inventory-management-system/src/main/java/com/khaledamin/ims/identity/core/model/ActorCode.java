package com.khaledamin.ims.identity.core.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.khaledamin.ims.identity.core.exception.IdentityValidationException;

import java.util.Locale;


public record ActorCode(@JsonValue String value) {

    private static final int MAX_LENGTH = 100;

    private static final String PATTERN = "^[A-Z0-9]+(?:[_-][A-Z0-9]+)*$";



    public ActorCode {
        value = normalize(value);
        validate(value);
    }

    /**
     * Factory method for creating a validated {@link ActorCode}.
     *
     * @param value {@link String} raw actor code
     * @return code {@link ActorCode} validated actor code
     */
    public static ActorCode of(String value) {
        return new ActorCode(value);
    }

    /**
     * Returns whether this actor code matches another actor code.
     *
     * @param other {@link ActorCode} other actor code
     * @return true if equal
     */
    public boolean sameAs(ActorCode other) {
        return other != null && value.equals(other.value);
    }


    @Override
    public String toString() {
        return value;
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim().toUpperCase(Locale.ROOT);
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw IdentityValidationException.invalidActorCode()
                    .withClientDetails("reason", "Actor code must not be null or blank");
        }

        if (value.length() > MAX_LENGTH) {
            throw IdentityValidationException.invalidActorCode()
                    .withDebugDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }

        if (!value.matches(PATTERN)) {
            throw IdentityValidationException.invalidActorCode()
                    .withDebugDetails("receivedValue", value)
                    .withDebugDetails("pattern", PATTERN);
        }
    }
}