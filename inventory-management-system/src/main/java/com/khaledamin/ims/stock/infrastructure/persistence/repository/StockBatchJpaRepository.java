package com.khaledamin.ims.stock.infrastructure.persistence.repository;

import com.khaledamin.ims.core.persistence.BaseRepository;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StockBatchJpaRepository extends
        BaseRepository<StockBatch, Long>,
        JpaSpecificationExecutor<StockBatch>
{

    List<StockBatch> findByStockCodeAndAvailableQuantityGreaterThan(
            String stockCode,
            Long quantity,
            Sort sort
    );
}