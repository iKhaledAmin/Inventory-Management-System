package com.khaledamin.ims.stock.api.mapper;

import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.stock.api.dto.StockBatchResponse;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface StockBatchMapper extends BaseMapper<StockBatchResponse, StockBatch> {

    @Override
    @Mapping(target = "consumedQuantity", expression = "java(batch.getConsumedQuantity())")
    @Mapping(target = "stockValue", expression = "java(batch.getStockValue())")
    @Mapping(target = "expired", expression = "java(batch.isExpired())")
    StockBatchResponse toResponse(StockBatch batch);
}