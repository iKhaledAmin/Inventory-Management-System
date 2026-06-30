package com.khaledamin.ims.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;



@Getter
@SuperBuilder
@Schema(
        name = "Meta",
        description = "Metadata for API responses"
)
public class Meta {

    @Schema(
            description = "Response timestamp",
            example = "2026-06-11T15:30:00"
    )
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @Schema(
            description = "Request correlation identifier",
            example = "01JX8H4Y9Z9X4K7T3S8A1B2C3D"
    )
    @JsonProperty("request_id")
    private String requestId;

}