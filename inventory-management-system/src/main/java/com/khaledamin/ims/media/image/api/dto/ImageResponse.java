package com.khaledamin.ims.media.image.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(name = "ImageResponse")
public class ImageResponse {

    @Schema(
            description = "Image code"
    )
    @JsonProperty("code")
    private String code;


    @Schema(
            description = "Available image variants"
    )
    @JsonProperty("variants")
    private List<ImageVariantResponse> variants;
}