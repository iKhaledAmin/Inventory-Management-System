package com.khaledamin.ims.organization.api.dto;

import com.khaledamin.ims.media.image.api.dto.ImageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@Schema(
        name = "OrganizationResponse",
        description = "Organization details"
)
public class OrganizationResponse {
    @Schema(example = "ORG-01JY8A7R4W7KX2N8QF5M6P9T3")
    private String code;

    @Schema(example = "Amazon")
    private String name;

    @Schema(example = "Online Shopping")
    private String description;

    @Schema(example = "ACTIVE")
    private String status;


    @Schema(
            name = "image",
            description = "Image details"
    )
    private ImageResponse image;
}
