package com.khaledamin.ims.core.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(
        name = "ActionResponse",
        description = "Simple action result response"
)
public class ApiActionResponse {

    @Schema(example = "Account activated successfully")
    private String message;
}