package com.khaledamin.ims.stock.application.service.impl;

import com.khaledamin.ims.stock.domain.model.StockAllocationStrategy;
import com.khaledamin.ims.stock.domain.model.StockBatchSortField;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class StockFIFOAllocationPlanner extends AbstractStockAllocationPlanner {

    public StockFIFOAllocationPlanner(StockQueryServiceImpl stockQueryService) {
        super(stockQueryService);
    }

    @Override
    public StockAllocationStrategy getStrategy() {
        return StockAllocationStrategy.FIFO;
    }

    @Override
    protected Sort getSort() {

        return Sort.by(
                StockBatchSortField.RECEIVED_DATE.getField()
        ).ascending();
    }
}