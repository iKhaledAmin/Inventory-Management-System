package com.khaledamin.ims.stock.application.model;

import com.khaledamin.ims.stock.domain.model.StockBatch;

import lombok.Builder;

@Builder
public record StockBatchAllocationPlan(StockBatch stockBatch, long quantity) {

    public static StockBatchAllocationPlan of(StockBatch stockBatch, long quantity) {
        return new StockBatchAllocationPlan(stockBatch, quantity);
    }
}