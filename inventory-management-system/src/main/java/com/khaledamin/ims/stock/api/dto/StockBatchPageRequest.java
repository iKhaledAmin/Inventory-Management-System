package com.khaledamin.ims.stock.api.dto;

import com.khaledamin.ims.core.api.pagination.PageRequest;
import com.khaledamin.ims.stock.domain.model.StockBatchSortField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "StockBatchPageRequest",
        description = "Pagination, sorting and filtering request for stock batches"
)
public class StockBatchPageRequest extends PageRequest {

    @Schema(
            description = "Stock sorting field",
            allowableValues = {
                    "RECEIVED_DATE",
                    "EXPIRATION_DATE",
                    "EXPIRATION_DATE",
                    "RECEIVED_QUANTITY",
                    "AVAILABLE_QUANTITY",
                    "UNIT_COST"
            },
            example = "RECEIVED_DATE"
    )
    private String sortBy = StockBatchSortField.getDefault();

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

    @Override
    public String getSortBy() {
        return StockBatchSortField.getFieldFrom(sortBy);
    }
}