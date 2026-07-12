package com.khaledamin.ims.stock.application.service;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.stock.api.dto.*;
import com.khaledamin.ims.stock.domain.model.Stock;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import com.khaledamin.ims.stock.domain.value.StockCode;

public interface StockManagementService {

    Stock create(StockCreateRequest request);

    Stock update(StockCode stockCode, StockUpdateRequest request);

    void delete(StockCode stockCode);

    Stock restock(StockCode stockCode, RestockRequest request);

    Stock view(StockCode stockCode);

    PageResult<Stock> list(StockPageRequest request);

    PageResult<StockBatch> listBatches(StockCode stockCode, StockBatchPageRequest request);


    boolean checkStockExistence(StockCode stockCode);
}
