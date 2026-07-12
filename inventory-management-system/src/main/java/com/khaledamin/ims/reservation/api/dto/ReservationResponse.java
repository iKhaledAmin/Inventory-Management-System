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
        name = "ReservationResponse",
        description = "Result of a stock reservation request."
)
public class ReservationResponse {

    @Schema(
            description = "Indicates whether the reservation operation was completed successfully.",
            example = "true"
    )
    @JsonProperty("success")
    private boolean success;

    @Schema(
            description = "Reservation information returned when the reservation succeeds. Null when reservation fails."
    )
    @JsonProperty("reservation_info")
    private ReservationInfo reservationInfo;

    @Schema(
            description = """
                    Collection of stock items that could not be reserved due to insufficient quantity.
                    Empty when the reservation succeeds.
                    """
    )
    @JsonProperty("unavailable_item_infos")
    private List<UnavailableItemInfo> unavailableItemInfos;
}