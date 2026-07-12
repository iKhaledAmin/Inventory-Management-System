package com.khaledamin.ims.stock.application.service.impl;

import com.khaledamin.ims.stock.application.model.StockBatchAllocationPlan;
import com.khaledamin.ims.stock.application.model.StockAllocationPlan;
import com.khaledamin.ims.stock.application.service.StockAllocationPlanner;
import com.khaledamin.ims.stock.domain.model.Stock;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import com.khaledamin.ims.stock.domain.value.StockCode;
import com.khaledamin.ims.stock.domain.value.StockQuantity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public abstract class AbstractStockAllocationPlanner implements StockAllocationPlanner {

    protected final StockQueryServiceImpl stockQueryService;

    protected abstract Sort getSort();

    @Override
    public StockAllocationPlan plan(StockCode stockCode, StockQuantity quantity) {

        Stock stock = stockQueryService.getByCode(stockCode);

        List<StockBatch> batches =
                stockQueryService.getAvailableBatches(
                        stockCode,
                        getSort()
                );

        long requestedQuantity = quantity.value();
        long availableQuantity = stock.getAvailableQuantity();

        if (availableQuantity < requestedQuantity) {

            return StockAllocationPlan.of(
                    stock,
                    false,
                    requestedQuantity,
                    availableQuantity,
                    List.of()
            );
        }

        List<StockBatchAllocationPlan> allocationPlans = buildBatchAllocationPlans(batches, requestedQuantity);

        return StockAllocationPlan.of(
                stock,
                true,
                requestedQuantity,
                availableQuantity,
                allocationPlans
        );
    }

    private List<StockBatchAllocationPlan> buildBatchAllocationPlans(
            List<StockBatch> batches,
            long requestedQuantity
    ) {

        long remaining = requestedQuantity;

        List<StockBatchAllocationPlan> allocations = new ArrayList<>();

        for (StockBatch batch : batches) {

            if (remaining == 0) {
                break;
            }

            long allocatedQuantity = Math.min(
                    remaining,
                    batch.getAvailableQuantity()
            );

            allocations.add(
                    StockBatchAllocationPlan.of(
                            batch,
                            allocatedQuantity
                    )
            );

            remaining -= allocatedQuantity;
        }

        return allocations;
    }
}