package com.khaledamin.ims.reservation.api.documentation.schema;

import com.khaledamin.ims.core.api.response.Meta;
import com.khaledamin.ims.reservation.api.dto.ReservationResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ReservationApiResponse"
)
public class ReservationApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public ReservationResponse data;
}