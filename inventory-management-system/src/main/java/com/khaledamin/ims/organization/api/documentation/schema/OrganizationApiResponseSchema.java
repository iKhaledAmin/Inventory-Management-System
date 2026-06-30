package com.khaledamin.ims.organization.api.documentation.schema;

import com.khaledamin.ims.organization.api.dto.OrganizationResponse;
import com.khaledamin.ims.core.api.response.Meta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "OrganizationApiResponse"
)
public class OrganizationApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public OrganizationResponse data;
}