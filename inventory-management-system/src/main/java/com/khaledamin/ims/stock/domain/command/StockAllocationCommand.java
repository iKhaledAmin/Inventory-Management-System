package com.khaledamin.ims.stock.domain.command;

import com.khaledamin.ims.stock.domain.value.StockCode;
import com.khaledamin.ims.stock.domain.value.StockQuantity;

public record StockAllocationCommand(
        StockCode stockCode,
        StockQuantity quantity
) {
    public static StockAllocationCommand of(String stockCode, Long quantity) {
        return new StockAllocationCommand(
                StockCode.of(stockCode),
                StockQuantity.of(quantity)
        );
    }
}
