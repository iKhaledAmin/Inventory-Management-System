package com.khaledamin.ims.identity.client.domain.value;

import com.khaledamin.ims.identity.client.domain.exception.ClientValidationException;

public record ClientName(String value) {

    public static final String NULL_ERROR_MESSAGE = "Client name is mandatory";

    public static final int MAX_LENGTH = 120;
    public static final String MAX_LENGTH_ERROR_MESSAGE =
            "Client name must be at most 120 characters";

    public ClientName {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim().replaceAll("\\s+", " ");
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw ClientValidationException.invalidName()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw ClientValidationException.invalidName()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length());
        }
    }

    public static ClientName of(String value) {
        return new ClientName(value);
    }

    @Override
    public String toString() {
        return value;
    }
}