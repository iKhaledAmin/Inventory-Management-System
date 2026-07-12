package com.khaledamin.ims.stock.api.mapper;

import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.media.image.api.mapper.ImageMapper;
import com.khaledamin.ims.stock.api.dto.StockDetailedResponse;
import com.khaledamin.ims.stock.domain.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = GlobalMapperConfig.class,
        uses = ImageMapper.class
)
public interface StockDetailsMapper extends BaseMapper<StockDetailedResponse, Stock> {

    @Override
    @Mapping(target = "totalBatchCount", expression = "java(stock.getBatchCount())")
    @Mapping(target = "totalReceivedQuantity", expression = "java(stock.getReceivedQuantity())")
    @Mapping(target = "totalAvailableQuantity", expression = "java(stock.getAvailableQuantity())")
    @Mapping(target = "totalReservedQuantity", expression = "java(stock.getReservedQuantity())")
    @Mapping(target = "totalConsumedQuantity", expression = "java(stock.getConsumedQuantity())")
    @Mapping(target = "totalStockValue", expression = "java(stock.getStockValue())")
    @Mapping(target = "nearestExpirationDate", expression = "java(stock.getNearestExpirationDate().orElse(null))")
    StockDetailedResponse toResponse(Stock stock);
}