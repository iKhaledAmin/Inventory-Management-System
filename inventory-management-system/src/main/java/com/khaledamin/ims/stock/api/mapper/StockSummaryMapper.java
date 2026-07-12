package com.khaledamin.ims.stock.api.mapper;

import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.media.image.api.mapper.ImageMapper;
import com.khaledamin.ims.stock.api.dto.StockSummaryResponse;
import com.khaledamin.ims.stock.domain.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = GlobalMapperConfig.class,
        uses = ImageMapper.class
)
public interface StockSummaryMapper extends BaseMapper<StockSummaryResponse, Stock> {

    @Override
    @Mapping(target = "imageUrl", source = "image", qualifiedByName = "thumbnailUrl")
    StockSummaryResponse toResponse(Stock stock);
}
