package com.khaledamin.ims.stock.domain.value;


import com.khaledamin.ims.stock.exception.StockValidationException;

public record StockName(String value) {

    public static final String NULL_ERROR_MESSAGE = "Stock name must not be null or empty";

    public static final int MAX_LENGTH = 150;

    public static final String MAX_LENGTH_ERROR_MESSAGE = "Stock name exceeds maximum allowed length";

    /**
     * Allows:
     * Letters
     * Numbers
     * Spaces
     * Hyphen
     * Apostrophe
     * Parentheses
     * Slash
     * Dot
     */
    public static final String PATTERN = "^[A-Za-z0-9\\-/'(). ]+$";

    public static final String PATTERN_ERROR_MESSAGE = "Stock name contains unsupported characters";

    public StockName {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim().replaceAll("\\s+", " ");
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw StockValidationException.invalidName()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw StockValidationException.invalidName()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }

        if (!value.matches(PATTERN)) {
            throw StockValidationException.invalidName()
                    .withClientDetails("reason", PATTERN_ERROR_MESSAGE)
                    .withClientDetails("expectedFormat", "product_name (e.g. 'Product Name')")
                    .withDebugDetails("receivedValue", value)
                    .withDebugDetails("pattern", PATTERN);
        }
    }

    public static StockName of(String value) {
        return new StockName(value);
    }

    @Override
    public String toString() {
        return value;
    }
}