package com.khaledamin.ims.organization.domain.value;


import com.khaledamin.ims.organization.exception.OrganizationValidationException;

public record OrganizationDescription(String value) {

    public static final String MAX_LENGTH_ERROR_MESSAGE = "Organization description is too long";
    public static final int MAX_LENGTH = 255;

    public OrganizationDescription {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {

        if (value == null) {
            return null;
        }

        value = value.trim();

        return value.isBlank() ? null : value;
    }

    private static void validate(String value) {

        // optional field
        if (value == null) {
            return;
        }

        if (value.length() > MAX_LENGTH) {
            throw OrganizationValidationException.invalidDescription()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }
    }

    public static OrganizationDescription of(String value) {
        return new OrganizationDescription(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
