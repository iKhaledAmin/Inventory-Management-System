package com.khaledamin.ims.identity.client.domain.value;

import com.khaledamin.ims.identity.client.domain.exception.ClientValidationException;

public record ClientRawSecret(String value) {

    public static final String NULL_ERROR_MESSAGE = "Client secret is mandatory";

    public static final int MIN_LENGTH = 12;
    public static final int MAX_LENGTH = 128;

    public static final String SIZE_ERROR_MESSAGE =
            "Client secret must be between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters";

    /**
     * Strong secret policy:
     * - uppercase
     * - lowercase
     * - digit
     * - special character
     */
    public static final String PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$";

    public static final String PATTERN_ERROR_MESSAGE =
            "Secret must contain uppercase, lowercase, digit, and special character";

    public ClientRawSecret {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw ClientValidationException.invalidSecret()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw ClientValidationException.invalidSecret()
                    .withClientDetails("reason", SIZE_ERROR_MESSAGE)
                    .withClientDetails("minLength", MIN_LENGTH)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length());
        }

        if (!value.matches(PATTERN)) {
            throw ClientValidationException.invalidSecret()
                    .withClientDetails("reason", PATTERN_ERROR_MESSAGE)
                    .withDebugDetails("receivedValue", "[PROTECTED]");
        }
    }

    public static ClientRawSecret of(String value) {
        return new ClientRawSecret(value);
    }

    @Override
    public String toString() {
        return "[PROTECTED]";
    }
}