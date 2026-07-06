package com.khaledamin.ims.stock.api.documentation.schema;

import com.khaledamin.ims.core.api.response.Meta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "StockExistenceApiResponse",
        description = "Standard API response wrapper for stock existence check"
)
public class StockExistenceApiSchema {

    @Schema
    public Meta meta;

    @Schema(
            description = "Stock existence result payload",
            implementation = com.khaledamin.ims.stock.api.dto.StockExistenceResponse.class
    )
    public boolean data;
}