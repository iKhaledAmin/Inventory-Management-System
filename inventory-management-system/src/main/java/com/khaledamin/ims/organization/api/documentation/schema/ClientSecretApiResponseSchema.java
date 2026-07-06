package com.khaledamin.ims.organization.api.documentation.schema;

import com.khaledamin.ims.core.api.response.Meta;
import com.khaledamin.ims.identity.client.api.dto.ClientSecretResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ClientSecretApiResponse"
)
public class ClientSecretApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public ClientSecretResponse data;
}