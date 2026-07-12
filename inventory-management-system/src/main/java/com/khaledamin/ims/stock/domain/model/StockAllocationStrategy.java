package com.khaledamin.ims.stock.domain.model;

public enum StockAllocationStrategy {

    FIFO, // first in first out
    LIFO, // last in first out
    FEFO  // first expired first out

    ;

    public static StockAllocationStrategy getDefault() {
        return FIFO;
    }
}