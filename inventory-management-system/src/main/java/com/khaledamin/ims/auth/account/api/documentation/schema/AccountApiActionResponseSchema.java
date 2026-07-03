package com.khaledamin.ims.auth.account.api.documentation.schema;

import com.khaledamin.ims.core.api.response.ApiActionResponse;
import com.khaledamin.ims.core.api.response.Meta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "AccountApiActionResponse"
)
public class AccountApiActionResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public ApiActionResponse data;
}