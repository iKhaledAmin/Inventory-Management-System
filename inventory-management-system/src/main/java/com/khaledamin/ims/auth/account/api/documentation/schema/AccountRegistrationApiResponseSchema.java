package com.khaledamin.ims.auth.account.api.documentation.schema;

import com.khaledamin.ims.auth.account.api.dto.AccountRegistrationResponse;
import com.khaledamin.ims.core.api.response.Meta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "AccountRegistrationApiResponse"
)
public class AccountRegistrationApiResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public AccountRegistrationResponse data;
}