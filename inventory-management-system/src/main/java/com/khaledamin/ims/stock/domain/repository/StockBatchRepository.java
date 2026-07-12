package com.khaledamin.ims.stock.domain.repository;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.stock.api.dto.StockBatchPageRequest;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StockBatchRepository {

    PageResult<StockBatch> findAllByStockCode(String stockCode, StockBatchPageRequest request);


    List<StockBatch> findAvailableByStockCode(String stockCode, Sort sort);

}