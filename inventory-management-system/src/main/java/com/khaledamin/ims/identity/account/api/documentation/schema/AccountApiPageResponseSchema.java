package com.khaledamin.ims.identity.account.api.documentation.schema;

import com.khaledamin.ims.core.api.response.Meta;
import com.khaledamin.ims.core.api.response.PageInfoResponse;
import com.khaledamin.ims.identity.account.api.dto.AccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(
        name = "AccountApiPageResponse"
)
public class AccountApiPageResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public List<AccountResponse> data;

    @Schema
    public PageInfoResponse pageInfo;
}