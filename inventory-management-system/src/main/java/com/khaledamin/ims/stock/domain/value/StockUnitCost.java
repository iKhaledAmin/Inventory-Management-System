package com.khaledamin.ims.stock.domain.value;

import com.khaledamin.ims.stock.exception.StockValidationException;

import java.math.BigDecimal;

public record StockUnitCost(BigDecimal value) {

    public static final String NULL_ERROR_MESSAGE = "Stock unit cost must not be null";

    public static final String NEGATIVE_ERROR_MESSAGE = "Stock unit cost must be greater than or equal to zero";

    public static final BigDecimal MIN_PRICE = BigDecimal.ZERO;

    public StockUnitCost {
        validate(value);
    }

    private static void validate(BigDecimal value) {

        if (value == null) {
            throw StockValidationException.invalidUnitCost()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value.compareTo(MIN_PRICE) < 0) {
            throw StockValidationException.invalidUnitCost()
                    .withClientDetails("reason", NEGATIVE_ERROR_MESSAGE)
                    .withDebugDetails("receivedValue", value);
        }
    }

    public static StockUnitCost of(BigDecimal value) {
        return new StockUnitCost(value);
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}