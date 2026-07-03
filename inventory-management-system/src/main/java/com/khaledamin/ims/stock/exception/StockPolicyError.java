package com.khaledamin.ims.stock.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.policy.PolicyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockPolicyError implements PolicyError {

    UPDATE_FORBIDDEN(
            SystemDomain.STOCK,
            "STOCK_UPDATE_FORBIDDEN",
            "Update stock is forbidden"
    ),

    DELETE_FORBIDDEN(
            SystemDomain.STOCK,
            "STOCK_DELETE_FORBIDDEN",
            "Delete stock is forbidden"
    ),

    VIEW_FORBIDDEN(
            SystemDomain.STOCK,
            "STOCK_VIEW_FORBIDDEN",
            "View stock is forbidden"
    ),

    RESTOCK_FORBIDDEN(
            SystemDomain.STOCK,
            "STOCK_RESTOCK_FORBIDDEN",
            "Restock is forbidden"
    ),

    LIST_BATCHES_FORBIDDEN(
            SystemDomain.STOCK,
            "STOCK_LIST_BATCHES_FORBIDDEN",
            "List batches is forbidden"
    );
    private final SystemDomain domain;
    private final String code;
    private final String message;
}
