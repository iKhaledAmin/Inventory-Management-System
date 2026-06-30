package com.khaledamin.ims.identity.account.domain.value;

import com.khaledamin.ims.identity.account.exception.AccountValidationException;

public record RawPassword(String value) {

    public static final String NULL_ERROR_MESSAGE = "Password must is mandatory";

    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 100;
    public static final String SIZE_ERROR_MESSAGE = "Password must be between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters";

    public static final String PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$";
    public static final String PATTERN_ERROR_MESSAGE = "Password must contain uppercase, lowercase, digit and special character";

    public RawPassword {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw AccountValidationException.invalidPassword()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value.length() < MIN_LENGTH) {
            throw AccountValidationException.invalidPassword()
                    .withClientDetails("reason", SIZE_ERROR_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw AccountValidationException.invalidPassword()
                    .withClientDetails("reason", SIZE_ERROR_MESSAGE);
        }

        if (!value.matches(PATTERN)) {
            throw AccountValidationException.invalidPassword()
                    .withClientDetails("reason", PATTERN_ERROR_MESSAGE);
        }
    }

    public static RawPassword of(String value) {
        return new RawPassword(value);
    }

    @Override
    public String toString() {
        return value;
    }
}