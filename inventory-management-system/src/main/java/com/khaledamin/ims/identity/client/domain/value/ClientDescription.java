package com.khaledamin.ims.identity.client.domain.value;

import com.khaledamin.ims.identity.client.domain.exception.ClientValidationException;

public record ClientDescription(String value) {

    public static final int MAX_LENGTH = 500;

    public ClientDescription {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private static void validate(String value) {

        if (value == null) {
            return; // optional field
        }

        if (value.length() > MAX_LENGTH) {
            throw ClientValidationException.invalidDescription()
                    .withClientDetails("reason","Client description must be at most " + MAX_LENGTH + " characters");
        }
    }

    public static ClientDescription of(String value) {
        return new ClientDescription(value);
    }

    @Override
    public String toString() {
        return value;
    }
}