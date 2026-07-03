package com.khaledamin.ims.stock.api.documentation.schema;

import com.khaledamin.ims.core.api.response.Meta;
import com.khaledamin.ims.core.api.response.PageInfoResponse;
import com.khaledamin.ims.stock.api.dto.StockBatchResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(
        name = "StockBatchApiPageResponse"
)
public class StockBatchApiPageResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public List<StockBatchResponse> data;

    @Schema
    public PageInfoResponse pageInfo;
}
