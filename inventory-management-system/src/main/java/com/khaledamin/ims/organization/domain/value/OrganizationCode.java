package com.khaledamin.ims.organization.domain.value;

import com.khaledamin.ims.organization.exception.OrganizationValidationException;

import java.util.Locale;

public record OrganizationCode(String value) {

    public static final String NULL_ERROR_MESSAGE = "Organization code must not be null or empty";

    public OrganizationCode {
        value = normalize(value);
        validate(value);
    }


    private static String normalize(String value) {
        return value == null ? null : value.trim().toUpperCase(Locale.ROOT);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank()) {
            throw OrganizationValidationException.invalidCode()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE)
                    .withDebugDetails("organizationCode", value);
        }
    }

    public static OrganizationCode of(String value) {
        return new OrganizationCode(value);
    }

    @Override
    public String toString() {
        return value;
    }



}
