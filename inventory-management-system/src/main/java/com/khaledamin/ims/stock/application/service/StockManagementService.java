package com.khaledamin.ims.stock.application.service;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.stock.api.dto.*;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import com.khaledamin.ims.stock.domain.model.StockItem;
import com.khaledamin.ims.stock.domain.value.StockCode;

public interface StockManagementService {

    StockItem create(StockCreateRequest request);

    StockItem update(StockCode stockCode, StockUpdateRequest request);

    void delete(StockCode stockCode);

    StockItem restock(StockCode stockCode, RestockRequest request);

    StockItem view(StockCode stockCode);

    PageResult<StockItem> list(StockPageRequest request);

    PageResult<StockBatch> listBatches(StockCode stockCode, StockBatchPageRequest request);


}
