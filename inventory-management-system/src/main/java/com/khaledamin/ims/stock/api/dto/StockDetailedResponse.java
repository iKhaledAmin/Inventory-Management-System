package com.khaledamin.ims.stock.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaledamin.ims.media.image.api.dto.ImageResponse;
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
        name = "StockResponse",
        description = "Stock item response"
)
public class StockDetailedResponse {

    @Schema(example = "STK-01KABC123DEF456GHI789JKL")
    @JsonProperty("code")
    private String code;

    @Schema(example = "IPHONE17PRO-BLK-256")
    @JsonProperty("sku")
    private String sku;

    @Schema(example = "Apple iPhone 17 Pro")
    @JsonProperty("name")
    private String name;

    @Schema(example = "Latest Apple flagship smartphone")
    @JsonProperty("description")
    private String description;

    @Schema(example = "ACTIVE")
    @JsonProperty("status")
    private String status;

    @Schema(example = "3")
    @JsonProperty("total_batch_count")
    private Integer totalBatchCount;

    @Schema(example = "1000")
    @JsonProperty("total_received_quantity")
    private Long totalReceivedQuantity;

    @Schema(example = "500")
    @JsonProperty("total_available_quantity")
    private Long totalAvailableQuantity;

    @Schema(example = "100")
    @JsonProperty("total_reserved_quantity")
    private Long totalReservedQuantity;

    @Schema(example = "400")
    @JsonProperty("total_consumed_quantity")
    private Long totalConsumedQuantity;

    @Schema(example = "3937.5000")
    @JsonProperty("total_stock_value")
    private BigDecimal totalStockValue;

    @Schema(example = "2027-12-31")
    @JsonProperty("nearest_expiration_date")
    private LocalDate nearestExpirationDate;

    @Schema(
            name = "image",
            description = "Image details"
    )
    @JsonProperty("image")
    private ImageResponse image;
}