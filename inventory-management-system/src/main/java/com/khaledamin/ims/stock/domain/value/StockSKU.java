package com.khaledamin.ims.stock.domain.value;

import com.khaledamin.ims.stock.exception.StockValidationException;

import java.util.Locale;

public record StockSKU(String value) {

    public static final String NULL_ERROR_MESSAGE = "SKU must not be null or empty";

    public static final int MAX_LENGTH = 100;

    public static final String MAX_LENGTH_ERROR_MESSAGE = "SKU exceeds maximum allowed length";

    /**
     * Allows:
     * ABC-123
     * IPHONE15-BLK
     * LAPTOP-DELL-001
     */
    public static final String PATTERN = "^[A-Z0-9\\-_]+$";

    public static final String PATTERN_ERROR_MESSAGE = "SKU contains unsupported characters";

    public StockSKU {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {

        if (value == null) {
            return null;
        }

        return value.trim().toUpperCase(Locale.ROOT);
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw StockValidationException.invalidSku()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw StockValidationException.invalidSku()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }

        if (!value.matches(PATTERN)) {
            throw StockValidationException.invalidSku()
                    .withClientDetails("reason", PATTERN_ERROR_MESSAGE)
                    .withDebugDetails("receivedValue", value)
                    .withDebugDetails("pattern", PATTERN);
        }
    }

    public static StockSKU of(String value) {
        return new StockSKU(value);
    }

    @Override
    public String toString() {
        return value;
    }
}