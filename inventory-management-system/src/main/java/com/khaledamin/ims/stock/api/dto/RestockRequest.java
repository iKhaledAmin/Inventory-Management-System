package com.khaledamin.ims.stock.api.dto;

import com.khaledamin.ims.stock.domain.value.StockExpirationDate;
import com.khaledamin.ims.stock.domain.value.StockReceivedQuantity;
import com.khaledamin.ims.stock.domain.value.StockUnitCost;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "RestockRequest",
        description = "Stock restock request"
)
public class RestockRequest {

    @Schema(
            example = "100",
            description = "Quantity received in this batch",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = StockReceivedQuantity.NULL_ERROR_MESSAGE)
    @Positive(message = StockReceivedQuantity.INVALID_ERROR_MESSAGE)
    private Long quantity;

    @Schema(
            example = "2027-12-31",
            description = "Optional expiration date"
    )
    @Future(message = StockExpirationDate.PAST_DATE_ERROR_MESSAGE)
    private LocalDate expirationDate;

    @Schema(
            example = "15.7500",
            description = "Unit acquisition cost",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Unit cost must not be null")
    @DecimalMin(value = "0.0001", message = StockUnitCost.NEGATIVE_ERROR_MESSAGE)
    private BigDecimal unitCost;
}