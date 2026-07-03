package com.khaledamin.ims.stock.domain.model;

public enum StockItemStatus {
    ACTIVE,
    INACTIVE;

    public static StockItemStatus getDefault() {
        return ACTIVE;
    }
}