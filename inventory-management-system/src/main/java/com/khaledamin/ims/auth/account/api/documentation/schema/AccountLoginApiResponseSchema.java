package com.khaledamin.ims.auth.account.api.documentation.schema;

import com.khaledamin.ims.auth.account.api.dto.AccountLoginResponse;
import com.khaledamin.ims.core.api.response.Meta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "AccountLoginApiResponse"
)
public class AccountLoginApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public AccountLoginResponse data;
}