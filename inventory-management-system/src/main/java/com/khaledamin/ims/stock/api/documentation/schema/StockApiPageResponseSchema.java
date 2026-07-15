package com.khaledamin.ims.stock.api.documentation.schema;


import com.khaledamin.ims.core.api.response.Meta;
import com.khaledamin.ims.core.api.response.PageInfoResponse;
import com.khaledamin.ims.stock.api.dto.StockSummaryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(
        name = "StockApiPageResponse"
)
public class StockApiPageResponseSchema {

    @Schema
    public Meta meta;

    @Schema
    public List<StockSummaryResponse> data;

    @Schema
    public PageInfoResponse pageInfo;
}