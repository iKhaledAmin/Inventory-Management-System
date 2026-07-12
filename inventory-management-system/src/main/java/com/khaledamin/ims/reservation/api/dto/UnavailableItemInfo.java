package com.khaledamin.ims.reservation.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "UnavailableItemInfo",
        description = "Information about a stock item that could not be reserved due to insufficient available quantity."
)
public class UnavailableItemInfo {

    @Schema(
            description = "Unique stock code.",
            example = "STK-1001"
    )
    @JsonProperty("stock_code")
    private String stockCode;

    @Schema(
            description = "Requested quantity for reservation.",
            example = "4"
    )
    @JsonProperty("requested_quantity")
    private Long requestedQuantity;

    @Schema(
            description = "Currently available quantity.",
            example = "2"
    )
    @JsonProperty("available_quantity")
    private Long availableQuantity;



    public static UnavailableItemInfo of(String stockCode, Long requestedQuantity, Long availableQuantity) {
        return UnavailableItemInfo.builder()
                .stockCode(stockCode)
                .requestedQuantity(requestedQuantity)
                .availableQuantity(availableQuantity)
                .build();
    }
}