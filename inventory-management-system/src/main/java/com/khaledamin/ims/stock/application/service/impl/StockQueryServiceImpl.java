package com.khaledamin.ims.stock.application.service.impl;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.organization.domain.value.OrganizationCode;
import com.khaledamin.ims.stock.api.dto.StockBatchPageRequest;
import com.khaledamin.ims.stock.api.dto.StockPageRequest;
import com.khaledamin.ims.stock.application.service.StockQueryService;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import com.khaledamin.ims.stock.domain.model.StockItem;
import com.khaledamin.ims.stock.domain.repository.StockBatchRepository;
import com.khaledamin.ims.stock.domain.repository.StockRepository;
import com.khaledamin.ims.stock.domain.value.StockCode;
import com.khaledamin.ims.stock.domain.value.StockSKU;
import com.khaledamin.ims.stock.exception.StockBusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StockQueryServiceImpl implements StockQueryService {
    private final StockRepository stockRepository;
    private final StockBatchRepository stockBatchRepository;

    @Override
    public Optional<StockItem> getOptionalByCode(StockCode itemCode) {

        return stockRepository.findByCode(itemCode.toString());
    }

    @Override
    public StockItem getByCode(StockCode itemCode) {

        return getOptionalByCode(itemCode).orElseThrow(() -> StockBusinessException.notFound()
                .withClientDetails("reason", "Stock not found for given code")
                .withDebugDetails("itemCode", itemCode.toString())
        );
    }

    @Override
    public PageResult<StockItem> getAllByOrganizationCode(OrganizationCode organizationCode, StockPageRequest request) {

        return stockRepository.findAllByOrganizationCode(
                organizationCode.toString(),
                request
        );
    }

    @Override
    public PageResult<StockBatch> getBatchesByStockCode(StockCode stockCode, StockBatchPageRequest request) {

        return stockBatchRepository.findAllByStockCode(
                stockCode.toString(),
                request
        );
    }


    @Override
    public boolean existsBySkyAndOrganizationCode(StockSKU stockSKU, OrganizationCode organizationCode) {
        return stockRepository.existsBySkuAndOrganizationCode(
                stockSKU.toString(),
                organizationCode.toString()
        );
    }

    @Override
    public boolean existsByCodeAndOrganizationCode(StockCode stockCode, OrganizationCode organizationCode) {
        return stockRepository.existsByCodeAndOrganizationCode(
                stockCode.toString(),
                organizationCode.toString()
        );
    }
}
