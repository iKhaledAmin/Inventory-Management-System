package com.khaledamin.ims.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Schema(
        name = "ApiResponse",
        description = "Standard success response wrapper"
)
public class ApiResponse<T> {

    @Schema
    @JsonProperty("meta")
    private Meta meta;

    @Schema
    @JsonProperty("data")
    private T data;
}