package com.khaledamin.ims.organization.api.dto;

import com.khaledamin.ims.stock.domain.model.StockAllocationStrategy;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "OrganizationSettingsUpdateRequest",
        description = "Update organization settings request"
)
public class OrganizationSettingsUpdateRequest {

    @Schema(
            description = "Reservation expiration time in minutes",
            example = "10"
    )
    @Min(
            value = 1,
            message = "Reservation expiration minutes must be greater than zero"
    )
    private Long reservationExpirationMinutes;

    @Schema(
            description = "Stock allocation strategy",
            example = "FIFO",
            allowableValues = {
                    "FIFO",
                    "LIFO",
                    "FEFO",
                    ""
            }
    )
    private StockAllocationStrategy allocationStrategy;
}