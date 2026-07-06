package com.khaledamin.ims.stock.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(
        name = "StockExistenceResponse",
        description = "Response indicating whether a stock item exists in IMS"
)
public class StockExistenceResponse {

    @Schema(
            description = "Stock business identifier",
            example = "STK-01KABC123DEF456GHI789JKL"
    )
    @JsonProperty("stock_code")
    private String stockCode;

    @Schema(
            description = "Indicates if stock exists in inventory system",
            example = "true"
    )
    @JsonProperty("exists")
    private boolean exists;
}