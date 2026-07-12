package com.khaledamin.ims.stock.application.service.impl;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.organization.domain.value.OrganizationCode;
import com.khaledamin.ims.stock.api.dto.StockBatchPageRequest;
import com.khaledamin.ims.stock.api.dto.StockPageRequest;
import com.khaledamin.ims.stock.application.service.StockQueryService;
import com.khaledamin.ims.stock.domain.model.Stock;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import com.khaledamin.ims.stock.domain.repository.StockBatchRepository;
import com.khaledamin.ims.stock.domain.repository.StockRepository;
import com.khaledamin.ims.stock.domain.value.StockCode;
import com.khaledamin.ims.stock.domain.value.StockSKU;
import com.khaledamin.ims.stock.exception.StockBusinessException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockQueryServiceImpl implements StockQueryService {
    private final StockRepository stockRepository;
    private final StockBatchRepository stockBatchRepository;

    @Override
    public Optional<Stock> getOptionalByCode(StockCode itemCode) {

        return stockRepository.findByCode(itemCode.toString());
    }

    @Override
    public Stock getByCode(StockCode stockCode) {

        return getOptionalByCode(stockCode).orElseThrow(() -> StockBusinessException.notFound()
                .withClientDetails("reason", "Stock not found for given code")
                .withDebugDetails("stockCode", stockCode.toString())
        );
    }

    @Override
    public PageResult<Stock> getAllByOrganizationCode(OrganizationCode organizationCode, StockPageRequest request) {

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

    @Override
    public List<StockBatch> getAvailableBatches(StockCode stockCode, Sort sort) {
        return stockBatchRepository.findAvailableByStockCode(
                stockCode.toString(),
                sort
        );
    }


}
