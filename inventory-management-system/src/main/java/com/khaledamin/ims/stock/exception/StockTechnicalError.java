package com.khaledamin.ims.stock.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockTechnicalError implements TechnicalError {

    IMAGE_NULL(
            SystemDomain.STOCK,
            "STOCK_IMAGE_NULL",
            "Stock image is null"
    ),


    ORGANIZATION_NULL(
            SystemDomain.STOCK,
            "STOCK_ORGANIZATION_NULL",
            "Organization is null"
    ),


    CREATE_COMMAND_NULL(
            SystemDomain.STOCK,
            "STOCK_CREATE_COMMAND_NULL",
            "Stock create command is null"
    ),

    UPDATE_COMMAND_NULL(
            SystemDomain.STOCK,
            "STOCK_UPDATE_COMMAND_NULL",
            "Stock update command is null"
    ),


    RESTOCK_COMMAND_NULL(
            SystemDomain.STOCK,
            "STOCK_RESTOCK_COMMAND_NULL",
            "Stock restock command is null"
    ),

    ITEM_NULL(
            SystemDomain.STOCK,
            "STOCK_ITEM_NULL",
            "Stock item is null"
    ),

    IMAGE_UPLOAD_FAILED(
            SystemDomain.STOCK,
            "STOCK_IMAGE_UPLOAD_FAILED",
            "Stock image upload failed"
    );
    private final SystemDomain domain;
    private final String code;
    private final String message;
}
