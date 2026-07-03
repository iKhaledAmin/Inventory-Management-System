package com.khaledamin.ims.auth.account.api.documentation.schema;

import com.khaledamin.ims.auth.account.api.dto.AccountActivationResponse;
import com.khaledamin.ims.core.api.response.Meta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "AccountActivationApiResponse"
)
public class AccountActivationApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public AccountActivationResponse data;
}