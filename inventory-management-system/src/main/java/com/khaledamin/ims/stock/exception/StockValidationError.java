package com.khaledamin.ims.stock.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockValidationError implements ValidationError {


    CODE_INVALID(
            SystemDomain.STOCK,
            "STOCK_CODE_INVALID",
            "Invalid stock code"
    ),

    NAME_INVALID(
            SystemDomain.STOCK,
            "STOCK_NAME_INVALID",
            "Invalid stock name"
    ),

    DESCRIPTION_INVALID(
            SystemDomain.STOCK,
            "STOCK_DESCRIPTION_INVALID",
            "Invalid stock description"
    ),


    EXPIRATION_DATE_INVALID(
            SystemDomain.STOCK,
            "STOCK_EXPIRATION_DATE_INVALID",
            "Invalid stock expiration date"
    ),


    RECEIVED_QUANTITY_INVALID(
            SystemDomain.STOCK,
            "STOCK_RECEIVED_QUANTITY_INVALID",
            "Invalid stock received quantity"
    ),

    SKU_INVALID(
            SystemDomain.STOCK,
            "STOCK_SKU_INVALID",
            "Invalid stock SKU"
    ),


    UNIT_COST_INVALID(
            SystemDomain.STOCK,
            "STOCK_UNIT_COST_INVALID",
            "Invalid stock unit cost"
    ),

    SORT_FIELD_INVALID(
            SystemDomain.STOCK,
            "STOCK_SORT_FIELD_INVALID",
            "Invalid stock sort field"
    );
    private final SystemDomain domain;
    private final String code;
    private final String message;
}
