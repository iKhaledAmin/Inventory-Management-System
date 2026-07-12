package com.khaledamin.ims.reservation.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ReservationRequest",
        description = "Request used to reserve stock items."
)
public class ReservationRequest {

    @Schema(
            description = "Stock items that should be reserved.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("stocks")
    private List<ReservationStockRequest> stocks;
}