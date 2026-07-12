package com.khaledamin.ims.reservation.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ReservationInfo",
        description = "Reservation details returned after a successful stock reservation."
)
public class ReservationInfo {

    @Schema(
            description = "Unique reservation identifier.",
            example = "RES-001"
    )
    @JsonProperty("reservation_code")
    private String reservationCode;

    @Schema(
            description = "Reservation expiration timestamp. The reservation is automatically released after this time if not confirmed.",
            example = "2026-06-24T15:15:00Z"
    )
    @JsonProperty("expires_at")
    private Instant expiresAt;
}