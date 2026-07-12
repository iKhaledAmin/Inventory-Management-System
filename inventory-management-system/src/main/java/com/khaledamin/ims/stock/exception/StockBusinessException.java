package com.khaledamin.ims.stock.exception;

import com.khaledamin.ims.core.exception.business.BusinessError;
import com.khaledamin.ims.core.exception.business.BusinessException;

public class StockBusinessException extends BusinessException {
    // ---------------------------------------------- Constructors ---------------------------------------------- //
    protected StockBusinessException(BusinessError error) {
        super(error);
    }
    // -------------------------------------------- End Constructors -------------------------------------------- //

    // -------------------------------------------- Static Methods -------------------------------------------- //
    public static StockBusinessException notFound() {
        return new StockBusinessException(StockBusinessError.NOT_FOUND);
    }

    public static StockBusinessException cannotDeleteNotAllowed() {
        return new StockBusinessException(StockBusinessError.DELETE_NOT_ALLOWED);
    }

    public static StockBusinessException SkuAlreadyExists() {
        return new StockBusinessException(StockBusinessError.SKU_ALREADY_EXISTS);
    }

    public static StockBusinessException insufficientStock() {
        return new StockBusinessException(StockBusinessError.INSUFFICIENT_STOCK);
    }

    public static StockBusinessException invalidReleaseQuantity() {
        return new StockBusinessException(StockBusinessError.INVALID_RELEASE_QUANTITY);
    }

    public static StockBusinessException invalidConsumeQuantity() {
        return new StockBusinessException(StockBusinessError.INVALID_CONSUME_QUANTITY);
    }


    // -------------------------------------------- End Static Methods -------------------------------------------- //
}

