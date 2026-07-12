package com.khaledamin.ims.organization.api.documentation.schema;

import com.khaledamin.ims.core.api.response.Meta;
import com.khaledamin.ims.organization.api.dto.OrganizationSettingsResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "OrganizationSettingsApiResponse"
)
public class OrganizationSettingsApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public OrganizationSettingsResponse data;
}