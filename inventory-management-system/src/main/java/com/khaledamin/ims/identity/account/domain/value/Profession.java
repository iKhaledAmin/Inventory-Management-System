package com.khaledamin.ims.identity.account.domain.value;

import com.khaledamin.ims.identity.account.exception.AccountValidationException;

public record Profession(String value) {

    public static final int MAX_LENGTH = 100;
    public static final String MAX_LENGTH_ERROR_MESSAGE = "Profession exceeds maximum allowed length";

    public Profession {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {

        if (value == null) {
            return null;
        }

        value = value.trim();

        return value.isBlank() ? null : value;
    }

    private static void validate(String value) {

        // optional field
        if (value == null) {
            return;
        }

        if (value.length() > MAX_LENGTH) {
            throw AccountValidationException.invalidProfession()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }
    }

    public static Profession of(String value) {
        return new Profession(value);
    }

    @Override
    public String toString() {
        return value;
    }
}