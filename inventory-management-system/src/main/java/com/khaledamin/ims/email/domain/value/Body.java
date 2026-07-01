package com.khaledamin.ims.email.domain.value;

import com.khaledamin.ims.email.exception.EmailValidationException;

public record Body(String value) {

    public Body {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim();
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw EmailValidationException.invalidBody()
                    .withClientDetails("reason", "Email body must not be null or empty");
        }
    }

    public static Body of(String value) {
        return new Body(value);
    }

}