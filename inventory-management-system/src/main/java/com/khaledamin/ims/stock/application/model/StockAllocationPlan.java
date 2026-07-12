package com.khaledamin.ims.stock.application.model;

import com.khaledamin.ims.stock.domain.model.Stock;
import lombok.Builder;

import java.util.List;

@Builder
public record StockAllocationPlan(
        Stock stock,
        boolean reservable,
        Long requestedQuantity,
        Long availableQuantity,
        List<StockBatchAllocationPlan> batchPlans
) {

    public static StockAllocationPlan of(
            Stock stock,
            boolean reservable,
            Long requestedQuantity,
            Long availableQuantity,
            List<StockBatchAllocationPlan> batchPlans
    ) {
        return StockAllocationPlan.builder()
                .stock(stock)
                .reservable(reservable)
                .requestedQuantity(requestedQuantity)
                .availableQuantity(availableQuantity)
                .batchPlans(batchPlans)
                .build();
    }

}
