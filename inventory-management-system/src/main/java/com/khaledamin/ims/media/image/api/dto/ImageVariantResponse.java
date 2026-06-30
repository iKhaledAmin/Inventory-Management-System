package com.khaledamin.ims.media.image.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "ImageVariantResponse")
public class ImageVariantResponse {

    @Schema(
            example = "ORIGINAL",
            description = "Image variant resolution label"
    )
    @JsonProperty("resolution")
    private String resolution;

    @Schema(
            example = "http://localhost:8080/media/images/product/IMG-01JTXABC/original.jpg",
            description = "Public URL used to access the image variant"
    )
    @JsonProperty("url")
    private String url;

    @Schema(
            example = "1200",
            description = "Actual image width in pixels"
    )
    @JsonProperty("width")
    private Integer width;

    @Schema(
            example = "900",
            description = "Actual image height in pixels"
    )
    @JsonProperty("height")
    private Integer height;
}