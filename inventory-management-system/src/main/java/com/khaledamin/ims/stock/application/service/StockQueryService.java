package com.khaledamin.ims.stock.application.service;


import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.organization.domain.value.OrganizationCode;
import com.khaledamin.ims.stock.api.dto.StockBatchPageRequest;
import com.khaledamin.ims.stock.api.dto.StockPageRequest;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import com.khaledamin.ims.stock.domain.model.StockItem;
import com.khaledamin.ims.stock.domain.value.StockCode;
import com.khaledamin.ims.stock.domain.value.StockSKU;

import java.util.Optional;

public interface StockQueryService {

    Optional<StockItem> getOptionalByCode(StockCode itemCode);
    StockItem getByCode(StockCode itemCode);

    PageResult<StockItem> getAllByOrganizationCode(OrganizationCode organizationCode, StockPageRequest request);

    PageResult<StockBatch> getBatchesByStockCode(StockCode stockCode, StockBatchPageRequest request);

    boolean existsBySkyAndOrganizationCode(StockSKU stockSKU, OrganizationCode organizationCode);

    boolean existsByCodeAndOrganizationCode(StockCode stockCode, OrganizationCode organizationCode);
}
