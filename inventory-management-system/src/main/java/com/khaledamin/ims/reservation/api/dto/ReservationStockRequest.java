package com.khaledamin.ims.reservation.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaledamin.ims.reservation.domain.value.ReservationQuantity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ReservationItemRequest",
        description = "Single stock reservation request."
)
public class ReservationStockRequest {

    @Schema(
            description = "Stock code.",
            example = "STK-1001",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("stock_code")
    private String stockCode;

    @Schema(
            description = "Requested quantity.",
            example = "5",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = ReservationQuantity.NULL_ERROR_MESSAGE)
    @Min(value = 1, message = ReservationQuantity.SIZE_ERROR_MESSAGE)
    @JsonProperty("quantity")
    private Long quantity;
}