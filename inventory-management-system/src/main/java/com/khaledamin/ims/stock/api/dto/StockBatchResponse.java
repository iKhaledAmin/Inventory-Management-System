package com.khaledamin.ims.stock.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "StockBatchResponse",
        description = "Stock batch response"
)
public class StockBatchResponse {

    @Schema(example = "BAT-01KABC123DEF456GHI789JKL")
    @JsonProperty("code")
    private String code;

    @Schema(example = "100")
    @JsonProperty("received_quantity")
    private Long receivedQuantity;

    @Schema(example = "20")
    @JsonProperty("consumed_quantity")
    private Long consumedQuantity;

    @Schema(example = "80")
    @JsonProperty("available_quantity")
    private Long availableQuantity;

    @Schema(example = "2026-07-01")
    @JsonProperty("received_date")
    private LocalDate receivedDate;

    @Schema(example = "2027-12-31")
    @JsonProperty("expiration_date")
    private LocalDate expirationDate;

    @Schema(example = "15.7500")
    @JsonProperty("unit_cost")
    private BigDecimal unitCost;

    @Schema(example = "1260.0000")
    @JsonProperty("stock_value")
    private BigDecimal stockValue;

    @Schema(example = "false")
    @JsonProperty("expired")
    private boolean expired;
}