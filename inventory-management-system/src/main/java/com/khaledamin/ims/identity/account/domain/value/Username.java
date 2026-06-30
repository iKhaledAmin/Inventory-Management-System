package com.khaledamin.ims.identity.account.domain.value;

import com.khaledamin.ims.identity.account.exception.AccountValidationException;

public record Username(String value) {

    public static final String NULL_ERROR_MESSAGE = "Username is mandatory";

    public static final int MAX_LENGTH = 100;
    public static final String MAX_LENGTH_ERROR_MESSAGE = "Username must be at most 100 characters long";

    /**
     * Canonical username format.
     */
    public static final String PATTERN = "^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*$";
    public static final String PATTERN_ERROR_MESSAGE =
            "Username may contain letters, numbers, dots, underscores, and hyphens";

    public Username {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw AccountValidationException.invalidUsername()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw AccountValidationException.invalidUsername()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }

        if (!value.matches(PATTERN)) {
            throw AccountValidationException.invalidUsername()
                    .withClientDetails("reason", PATTERN_ERROR_MESSAGE)
                    .withClientDetails("expectedFormat", "letters_numbers_dots_underscores")
                    .withDebugDetails("receivedValue", value)
                    .withDebugDetails("pattern", PATTERN);
        }
    }

    public static Username of(String value) {
        return new Username(value);
    }

    @Override
    public String toString() {
        return value;
    }
}