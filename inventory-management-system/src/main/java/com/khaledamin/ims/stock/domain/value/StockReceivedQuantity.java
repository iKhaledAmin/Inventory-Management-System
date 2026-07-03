package com.khaledamin.ims.stock.domain.value;

import com.khaledamin.ims.stock.exception.StockValidationException;

public record StockReceivedQuantity(Long value) {

    public static final String NULL_ERROR_MESSAGE = "Received quantity must not be null";

    public static final String INVALID_ERROR_MESSAGE = "Received quantity must be greater than zero";

    public StockReceivedQuantity {
        validate(value);
    }

    private static void validate(Long value) {

        if (value == null) {
            throw StockValidationException.invalidReceivedQuantity()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value <= 0) {
            throw StockValidationException.invalidReceivedQuantity()
                    .withClientDetails("reason", INVALID_ERROR_MESSAGE)
                    .withDebugDetails("receivedValue", value);
        }
    }

    public static StockReceivedQuantity of(Long value) {
        return new StockReceivedQuantity(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}