package com.khaledamin.ims.stock.domain.value;


import com.khaledamin.ims.stock.exception.StockValidationException;

import java.util.Locale;

public record StockCode(String value) {

    public static final String NULL_ERROR_MESSAGE = "Stock code must not be null or empty";

    public StockCode {
        value = normalize(value);
        validate(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim().toUpperCase(Locale.ROOT);
    }

    private static void validate(String value) {

        if (value == null || value.isBlank()) {
            throw StockValidationException.invalidCode()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE)
                    .withDebugDetails("itemCode", value);
        }
    }

    public static StockCode of(String value) {
        return new StockCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}