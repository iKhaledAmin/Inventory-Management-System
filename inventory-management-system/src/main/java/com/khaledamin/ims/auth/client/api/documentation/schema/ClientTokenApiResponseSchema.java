package com.khaledamin.ims.auth.client.api.documentation.schema;

import com.khaledamin.ims.auth.client.api.dto.ClientTokenResponse;
import com.khaledamin.ims.core.api.response.Meta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ClientTokenApiResponse"
)
public class ClientTokenApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public ClientTokenResponse data;
}