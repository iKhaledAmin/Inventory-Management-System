package com.khaledamin.ims.stock.domain.repository;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.stock.api.dto.StockPageRequest;
import com.khaledamin.ims.stock.domain.model.StockItem;

import java.util.Optional;

public interface StockRepository {
    StockItem save(StockItem stockItem);

    Optional<StockItem> findByCode(String itemCode);

    PageResult<StockItem> findAllByOrganizationCode(String organizationCode, StockPageRequest request);

    boolean existsBySkuAndOrganizationCode(String itemSku, String organizationCode);

    boolean existsByCodeAndOrganizationCode(String stockCode,String organizationCode);
}
