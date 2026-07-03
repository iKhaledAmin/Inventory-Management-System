package com.khaledamin.ims.stock.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(
        name = "StockBatchFilter",
        description = "Filters used when listing stock batches"
)
public class StockBatchFilter {

    @Schema(
            description = """
                    Returns only batches that still contain available stock.

                    true  -> only batches with available quantity > 0
                    false -> no filtering
                    """,
            example = "true"
    )
    private Boolean hasStock;

    @Schema(
            description = """
                    Returns only expired batches.

                    true  -> expiration date is before today
                    false -> no filtering
                    """,
            example = "false"
    )
    private Boolean expired;

    @Schema(
            description = """
                    Returns batches expiring before the specified date.
                    """,
            example = "2027-01-01"
    )
    private LocalDate expirationBefore;
}