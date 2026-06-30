package com.khaledamin.ims.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@Schema(
        name = "ApiPageResponse",
        description = "Standard pagination response wrapper"
)
public class ApiPageResponse<T> {

    @Schema
    @JsonProperty("meta")
    private Meta meta;

    @Schema
    @JsonProperty("data")
    private List<T> data;

    @Schema
    @JsonProperty("page_info")
    private PageInfoResponse pageInfo;

}