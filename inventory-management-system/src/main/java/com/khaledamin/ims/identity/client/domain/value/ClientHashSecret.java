package com.khaledamin.ims.identity.client.domain.value;

import com.khaledamin.ims.identity.client.domain.exception.ClientValidationException;

public record ClientHashSecret(String value) {

    public static final String NULL_ERROR_MESSAGE =
            "Secret hash must not be null or empty";

    public ClientHashSecret {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw ClientValidationException.invalidSecretHash()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        // optional: enforce encoding prefix like {bcrypt}
        if (!value.startsWith("$2a$") && !value.startsWith("$2b$")) {
            throw ClientValidationException.invalidSecretHash()
                    .withClientDetails("reason", "Secret hash must be bcrypt encoded");
        }
    }

    public static ClientHashSecret of(String value) {
        return new ClientHashSecret(value);
    }

    @Override
    public String toString() {
        return value;
    }
}