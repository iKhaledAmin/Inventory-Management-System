package com.khaledamin.ims.organization.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaledamin.ims.stock.domain.model.StockAllocationStrategy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "OrganizationSettingsResponse",
        description = "Organization settings details"
)
public class OrganizationSettingsResponse {

    @JsonProperty("reservation_expiration_minutes")
    @Schema(
            description = "Reservation expiration time in minutes",
            example = "5"
    )
    private Long reservationExpirationMinutes;

    @JsonProperty("allocation_strategy")
    @Schema(
            description = "Stock allocation strategy",
            example = "FIFO"
    )
    private StockAllocationStrategy allocationStrategy;
}