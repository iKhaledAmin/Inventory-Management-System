package com.khaledamin.ims.stock.api.mapper;

import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.media.image.api.mapper.ImageMapper;
import com.khaledamin.ims.stock.api.dto.StockDetailedResponse;
import com.khaledamin.ims.stock.domain.model.StockItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = GlobalMapperConfig.class,
        uses = ImageMapper.class
)
public interface StockDetailsMapper extends BaseMapper<StockDetailedResponse, StockItem> {

    @Override
    @Mapping(target = "totalBatchCount", expression = "java(item.getBatchCount())")
    @Mapping(target = "totalReceivedQuantity", expression = "java(item.getReceivedQuantity())")
    @Mapping(target = "totalConsumedQuantity", expression = "java(item.getConsumedQuantity())")
    @Mapping(target = "totalAvailableQuantity", expression = "java(item.getAvailableQuantity())")
    @Mapping(target = "totalStockValue", expression = "java(item.getStockValue())")
    @Mapping(target = "nearestExpirationDate", expression = "java(item.getNearestExpirationDate().orElse(null))")
    StockDetailedResponse toResponse(StockItem item);
}