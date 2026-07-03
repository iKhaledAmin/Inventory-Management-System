package com.khaledamin.ims.stock.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "StockSummaryResponse",
        description = "Summary stock item response with basic information"
)
public class StockSummaryResponse {
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

    @Schema(
            example = "http://localhost:8080/media/images/stock/thumbnail.jpg",
            description = "Stock item image URL"
    )
    @JsonProperty("image_url")
    private String imageUrl;
}
