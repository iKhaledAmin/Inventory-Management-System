package com.khaledamin.ims.stock.api.dto;

import com.khaledamin.ims.core.api.pagination.PageRequest;
import com.khaledamin.ims.stock.domain.model.StockSortField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "StockPageRequest",
        description = "Stock page request"
)
public class StockPageRequest extends PageRequest {
    @Schema(
            description = "Stock sorting field",
            allowableValues = {
                    "NAME",
                    "SKU",
                    "CREATED_AT"
            },
            example = "NAME"
    )
    private String sortBy = StockSortField.getDefault();



    @Override
    public String getSortBy() {
        return StockSortField.getFieldFrom(sortBy);
    }
}
