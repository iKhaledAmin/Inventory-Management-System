package com.khaledamin.ims.organization.domain.value;

import com.khaledamin.ims.organization.exception.OrganizationValidationException;

public record OrganizationName(String value) {


    public static final String NULL_ERROR_MESSAGE = "Organization name must not be null or empty";

    public static final int MAX_LENGTH = 50;
    public static final String MAX_LENGTH_ERROR_MESSAGE = "Organization name exceeds maximum allowed length";

    public static final String PATTERN = "^[A-Za-z0-9]+(?:[ .&'-][A-Za-z0-9]+)*$";
    public static final String PATTERN_ERROR_MESSAGE = "Organization name contains invalid characters";

    public OrganizationName {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim().replaceAll("\\s+", " ");
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw OrganizationValidationException.invalidName()
                    .withClientDetails("reason",NULL_ERROR_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw OrganizationValidationException.invalidName()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }

        if (!value.matches(PATTERN)) {
            throw OrganizationValidationException.invalidName()
                    .withClientDetails("reason",PATTERN_ERROR_MESSAGE)
                    .withClientDetails("expectedFormat", "letters_and_spaces_only")
                    .withDebugDetails("receivedValue", value)
                    .withDebugDetails("pattern", PATTERN);
        }
    }

    public static OrganizationName of(String value) {
        return new OrganizationName(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
