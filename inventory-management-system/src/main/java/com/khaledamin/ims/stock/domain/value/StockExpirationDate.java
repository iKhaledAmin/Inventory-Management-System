package com.khaledamin.ims.stock.domain.value;

import com.khaledamin.ims.stock.exception.StockValidationException;

import java.time.LocalDate;

public record StockExpirationDate(LocalDate value) {

    public static final String PAST_DATE_ERROR_MESSAGE = "Expiration date must not be in the past";

    public StockExpirationDate {
        validate(value);
    }

    private static void validate(LocalDate value) {

        if (value == null) {
            return;
        }

        if (value.isBefore(LocalDate.now())) {
            throw StockValidationException.invalidExpirationDate()
                    .withClientDetails("reason", PAST_DATE_ERROR_MESSAGE)
                    .withDebugDetails("receivedValue", value);
        }
    }

    public static StockExpirationDate of(LocalDate value) {
        return new StockExpirationDate(value);
    }

    @Override
    public String toString() {
        return value == null ? null : value.toString();
    }
}