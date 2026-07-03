package com.khaledamin.ims.stock.exception;

import com.khaledamin.ims.core.exception.validation.ValidationError;
import com.khaledamin.ims.core.exception.validation.ValidationException;

public class StockValidationException extends ValidationException {
    // ---------------------------------------------------- Constructors ---------------------------------------------------- //
    protected StockValidationException(ValidationError error) {
        super(error);
    }

    // ---------------------------------------------------- End Constructors -------------------------------------------------- //

    // ---------------------------------------------------- Methods -------------------------------------------------------- //

    public static StockValidationException invalidCode() {
        return new StockValidationException(StockValidationError.CODE_INVALID);
    }

    public static StockValidationException invalidItemName() {
        return new StockValidationException(StockValidationError.NAME_INVALID);
    }

    public static StockValidationException invalidItemDescription() {
        return new StockValidationException(StockValidationError.DESCRIPTION_INVALID);
    }

    public static StockValidationException invalidExpirationDate() {
        return new StockValidationException(StockValidationError.EXPIRATION_DATE_INVALID);
    }

    public static StockValidationException invalidReceivedQuantity() {
        return new StockValidationException(StockValidationError.RECEIVED_QUANTITY_INVALID);
    }

    public static StockValidationException invalidSku() {
        return new StockValidationException(StockValidationError.SKU_INVALID);
    }

    public static StockValidationException invalidUnitCost() {
        return new StockValidationException(StockValidationError.UNIT_COST_INVALID);
    }

    public static StockValidationException invalidSortField() {
        return new StockValidationException(StockValidationError.SORT_FIELD_INVALID);
    }


    // ---------------------------------------------------- End Methods ------------------------------------------------------ //
}
