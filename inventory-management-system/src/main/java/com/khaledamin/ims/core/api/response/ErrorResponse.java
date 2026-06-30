package com.khaledamin.ims.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;




@Getter
@SuperBuilder
@Schema(
        name = "Error",
        description = "Error response"
)
public class ErrorResponse {


    @Schema(example = "404")
    @JsonProperty("status")
    private int status;

    @Schema(example = "ACCOUNT_NOT_FOUND")
    @JsonProperty("code")
    private String code;

    @Schema(example = "Account not found")
    @JsonProperty("message")
    private String message;

    @Schema(example = "/api/v1/accounts/1")
    @JsonProperty("path")
    private String path;

    @Schema(
            description = "Additional error information details",
            example = "{\"username\" : [\"Username is mandatory\"]}"
    )
    @JsonProperty("details")
    private Map<String,?> details;
}