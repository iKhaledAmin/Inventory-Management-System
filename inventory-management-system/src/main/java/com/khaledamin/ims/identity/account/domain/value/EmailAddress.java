package com.khaledamin.ims.identity.account.domain.value;

import com.khaledamin.ims.identity.account.exception.AccountValidationException;

public record EmailAddress(String value) {

    public static final String NULL_ERROR_MESSAGE = "Email address is mandatory";


    public static final int MAX_LENGTH = 100;
    public static final String MAX_LENGTH_ERROR_MESSAGE = "Email address exceeds maximum allowed length";


    public static final String PATTERN = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
    public static final String PATTERN_ERROR_MESSAGE = "Email address format is invalid";



    public EmailAddress {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw AccountValidationException.invalidEmail()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw AccountValidationException.invalidEmail()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }

        if (!value.matches(PATTERN)) {
            throw AccountValidationException.invalidEmail()
                    .withClientDetails("reason", PATTERN_ERROR_MESSAGE)
                    .withClientDetails("expectedFormat", "example@domain.com")
                    .withDebugDetails("receivedValue", value)
                    .withDebugDetails("pattern", PATTERN);
        }
    }

    public static EmailAddress of(String value) {
        return new EmailAddress(value);
    }

    @Override
    public String toString() {
        return value;
    }
}