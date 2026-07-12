package com.khaledamin.ims.stock.domain.value;


import com.khaledamin.ims.stock.exception.StockValidationException;

public record StockDescription(String value) {

    public static final int MAX_LENGTH = 5000;

    public static final String MAX_LENGTH_ERROR_MESSAGE = "Stock description is too long";

    public StockDescription {
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

        if (value == null) {
            return;
        }

        if (value.length() > MAX_LENGTH) {
            throw StockValidationException.invalidDescription()
                    .withClientDetails("reason", MAX_LENGTH_ERROR_MESSAGE)
                    .withClientDetails("maxLength", MAX_LENGTH)
                    .withDebugDetails("actualLength", value.length())
                    .withDebugDetails("receivedValue", value);
        }
    }

    public static StockDescription of(String value) {
        return new StockDescription(value);
    }

    @Override
    public String toString() {
        return value;
    }
}