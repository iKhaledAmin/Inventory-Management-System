package com.khaledamin.ims.identity.client.domain.value;

import com.khaledamin.ims.identity.client.domain.exception.ClientValidationException;

public record ClientId(String value) {

    public static final String NULL_ERROR_MESSAGE = "Client id is mandatory";

    public static final int MAX_LENGTH = 120;
    public static final String MAX_LENGTH_ERROR_MESSAGE =
            "Client id must be at most 120 characters long";

    /**
     * Allowed format:
     * - letters, numbers, hyphen, underscore
     */
    public static final String PATTERN = "^[a-zA-Z0-9._-]+$";
    public static final String PATTERN_ERROR_MESSAGE =
            "Client id may only contain letters, numbers, dots, underscores, and hyphens";

    public ClientId {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw ClientValidationException.invalidId()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw ClientValidationException.invalidId()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }

        if (!value.matches(PATTERN)) {
            throw ClientValidationException.invalidId()
                    .withClientDetails("reason", PATTERN_ERROR_MESSAGE)
                    .withClientDetails("expectedFormat", "alphanumeric_dot_underscore_hyphen")
                    .withDebugDetails("receivedValue", value)
                    .withDebugDetails("pattern", PATTERN);
        }
    }

    public static ClientId of(String value) {
        return new ClientId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}