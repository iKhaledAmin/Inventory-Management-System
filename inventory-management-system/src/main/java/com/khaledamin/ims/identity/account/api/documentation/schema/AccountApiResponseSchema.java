package com.khaledamin.ims.identity.account.api.documentation.schema;

import com.khaledamin.ims.core.api.response.Meta;
import com.khaledamin.ims.identity.account.api.dto.AccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "AccountApiResponse"
)
public class AccountApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public AccountResponse data;
}