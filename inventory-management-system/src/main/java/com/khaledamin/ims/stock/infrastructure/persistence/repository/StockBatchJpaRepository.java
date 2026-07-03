package com.khaledamin.ims.stock.infrastructure.persistence.repository;

import com.khaledamin.ims.core.persistence.BaseRepository;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockBatchJpaRepository extends
        BaseRepository<StockBatch, Long>,
        JpaSpecificationExecutor<StockBatch>
{

}