package com.khaledamin.ims.identity.account.domain.value;

import com.khaledamin.ims.identity.account.exception.AccountValidationException;

public record EncodedPassword(String value) {

    public EncodedPassword {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw AccountValidationException.invalidPassword()
                    .withClientDetails("reason", "Account password must not be null or empty");
        }
    }

    public static EncodedPassword of(String value) {
        return new EncodedPassword(value);
    }

    @Override
    public String toString() {
        return value;
    }
}