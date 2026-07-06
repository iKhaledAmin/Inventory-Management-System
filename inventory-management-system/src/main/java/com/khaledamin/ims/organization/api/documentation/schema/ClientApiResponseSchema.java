package com.khaledamin.ims.organization.api.documentation.schema;

import com.khaledamin.ims.core.api.response.Meta;
import com.khaledamin.ims.identity.client.api.dto.ClientResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ClientApiResponse"
)
public class ClientApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public ClientResponse data;
}