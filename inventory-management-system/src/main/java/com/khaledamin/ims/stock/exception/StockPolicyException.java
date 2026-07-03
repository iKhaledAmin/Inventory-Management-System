package com.khaledamin.ims.stock.exception;

import com.khaledamin.ims.core.exception.policy.PolicyError;
import com.khaledamin.ims.core.exception.policy.PolicyException;

public class StockPolicyException extends PolicyException {
    // ---------------------------------------------- Constructors ---------------------------------------------- //
    protected StockPolicyException(PolicyError error) {
        super(error);
    }
    // -------------------------------------------- End Constructors -------------------------------------------- //

    // -------------------------------------------- Static Methods -------------------------------------------- //
    public static StockPolicyException forbiddenUpdate() {
        return new StockPolicyException(StockPolicyError.UPDATE_FORBIDDEN);
    }

    public static StockPolicyException forbiddenDelete() {
        return new StockPolicyException(StockPolicyError.DELETE_FORBIDDEN);
    }

    public static StockPolicyException forbiddenView() {
        return new StockPolicyException(StockPolicyError.VIEW_FORBIDDEN);
    }

    public static StockPolicyException forbiddenRestock() {
        return new StockPolicyException(StockPolicyError.RESTOCK_FORBIDDEN);
    }

    public static StockPolicyException forbiddenListBatches() {
        return new StockPolicyException(StockPolicyError.LIST_BATCHES_FORBIDDEN);
    }
    // -------------------------------------------- End Static Methods -------------------------------------------- //
}
