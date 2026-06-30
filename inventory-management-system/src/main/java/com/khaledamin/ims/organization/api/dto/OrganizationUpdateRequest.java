package com.khaledamin.ims.organization.api.dto;

import com.khaledamin.ims.organization.domain.value.OrganizationDescription;
import com.khaledamin.ims.organization.domain.value.OrganizationName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "CategoryUpdateRequest",
        description = "Update category request"
)
public class OrganizationUpdateRequest {


    @Schema(
            example = "Amazon",
            description = "Optional new name"
    )
    @Pattern(regexp = OrganizationName.PATTERN, message = OrganizationName.PATTERN_ERROR_MESSAGE)
    @Size(max = OrganizationName.MAX_LENGTH, message = OrganizationName.MAX_LENGTH_ERROR_MESSAGE)
    private String name;

    @Schema(
            example = "Online Shopping",
            description = "Optional new description"
    )
    @Size(max = OrganizationDescription.MAX_LENGTH, message = OrganizationDescription.MAX_LENGTH_ERROR_MESSAGE)
    private String description;


    @Schema(
            description = "Optional new image",
            type = "string",
            format = "binary"
    )
    private MultipartFile image;
}
