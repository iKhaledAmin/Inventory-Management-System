package com.khaledamin.ims.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@Schema(
        name = "PageInfo",
        description = "Page information"
)
public class PageInfoResponse {

    @Schema(example = "true")
    @JsonProperty("first")
    private boolean first;

    @Schema(example = "false")
    @JsonProperty("last")
    private boolean last;

    @Schema(example = "true")
    @JsonProperty("has_next")
    private boolean hasNext;

    @Schema(example = "false")
    @JsonProperty("has_previous")
    private boolean hasPrevious;

    @Schema(example = "0")
    @JsonProperty("page")
    private int page;

    @Schema(example = "20")
    @JsonProperty("size")
    private int size;

    @Schema(example = "125")
    @JsonProperty("total_elements")
    private long totalElements;

    @Schema(example = "7")
    @JsonProperty("total_pages")
    private long totalPages;

}