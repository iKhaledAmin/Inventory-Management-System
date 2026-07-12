package com.khaledamin.ims.stock.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.business.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StockBusinessError implements BusinessError {


    NOT_FOUND(
            SystemDomain.STOCK,
            "STOCK_NOT_FOUND",
            HttpStatus.NOT_FOUND,
            "Stock not found"
    ),

    DELETE_NOT_ALLOWED(
            SystemDomain.STOCK,
            "STOCK_DELETE_NOT_ALLOWED",
            HttpStatus.CONFLICT,
            "Cannot delete stock with available stock batches"
    ),

    SKU_ALREADY_EXISTS(
            SystemDomain.STOCK,
            "STOCK_SKU_ALREADY_EXISTS",
            HttpStatus.CONFLICT,
            "Stock SKU already exists"
    ),

    INSUFFICIENT_STOCK(
            SystemDomain.STOCK,
            "STOCK_INSUFFICIENT_STOCK",
            HttpStatus.CONFLICT,
            "Insufficient stock"
    ),

    INVALID_RELEASE_QUANTITY(
            SystemDomain.STOCK,
            "STOCK_INVALID_RELEASE_QUANTITY",
            HttpStatus.CONFLICT,
            "Invalid release quantity"
    ),

    INVALID_CONSUME_QUANTITY(
            SystemDomain.STOCK,
            "STOCK_INVALID_CONSUME_QUANTITY",
            HttpStatus.CONFLICT,
            "Invalid consume quantity"
    );
    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;
}
