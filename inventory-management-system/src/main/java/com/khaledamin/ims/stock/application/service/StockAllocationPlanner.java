package com.khaledamin.ims.stock.application.service;


import com.khaledamin.ims.stock.application.model.StockAllocationPlan;
import com.khaledamin.ims.stock.domain.model.StockAllocationStrategy;
import com.khaledamin.ims.stock.domain.value.StockCode;
import com.khaledamin.ims.stock.domain.value.StockQuantity;

public interface StockAllocationPlanner {

    StockAllocationStrategy getStrategy();

    StockAllocationPlan plan(StockCode stockCode, StockQuantity quantity);
}