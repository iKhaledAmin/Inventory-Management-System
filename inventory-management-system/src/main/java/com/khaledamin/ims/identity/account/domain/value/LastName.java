package com.khaledamin.ims.identity.account.domain.value;

import com.khaledamin.ims.identity.account.exception.AccountValidationException;

public record LastName(String value) {

    public static final String NULL_ERROR_MESSAGE = "Last name is mandatory";

    public static final int MAX_LENGTH = 50;
    public static final String MAX_LENGTH_ERROR_MESSAGE = "Last name exceeds maximum allowed length";


    public static final String PATTERN = "^[A-Za-z]+(?: [A-Za-z]+)*$";
    public static final String PATTERN_ERROR_MESSAGE = "Last name must contain only letters and spaces";

    public LastName {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim().replaceAll("\\s+", " ");
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw AccountValidationException.invalidLastName()
                    .withClientDetails("reason",NULL_ERROR_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw AccountValidationException.invalidLastName()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }

        if (!value.matches(PATTERN)) {
            throw AccountValidationException.invalidLastName()
                    .withClientDetails("reason", PATTERN_ERROR_MESSAGE)
                    .withClientDetails("expectedFormat", "letters_and_spaces_only")
                    .withDebugDetails("receivedValue", value)
                    .withDebugDetails("pattern", PATTERN);
        }
    }

    public static LastName of(String value) {
        return new LastName(value);
    }

    @Override
    public String toString() {
        return value;
    }
}