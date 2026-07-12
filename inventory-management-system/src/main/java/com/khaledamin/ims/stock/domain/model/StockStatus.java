package com.khaledamin.ims.stock.domain.model;

public enum StockStatus {
    ACTIVE,
    INACTIVE;

    public static StockStatus getDefault() {
        return ACTIVE;
    }
}