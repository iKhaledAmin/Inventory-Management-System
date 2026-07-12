package com.khaledamin.ims.stock.domain.repository;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.stock.api.dto.StockPageRequest;
import com.khaledamin.ims.stock.domain.model.Stock;

import java.util.Optional;

public interface StockRepository {
    Stock save(Stock stock);

    Optional<Stock> findByCode(String itemCode);

    PageResult<Stock> findAllByOrganizationCode(String organizationCode, StockPageRequest request);

    boolean existsBySkuAndOrganizationCode(String itemSku, String organizationCode);

    boolean existsByCodeAndOrganizationCode(String stockCode,String organizationCode);
}
