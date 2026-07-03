package com.khaledamin.ims.stock.api.documentation.schema;

import com.khaledamin.ims.core.api.response.Meta;
import com.khaledamin.ims.stock.api.dto.StockDetailedResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "StockApiResponse"
)
public class StockApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public StockDetailedResponse data;
}