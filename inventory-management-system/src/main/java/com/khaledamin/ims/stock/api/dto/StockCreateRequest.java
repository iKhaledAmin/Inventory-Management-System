package com.khaledamin.ims.stock.api.dto;

import com.khaledamin.ims.stock.domain.value.StockDescription;
import com.khaledamin.ims.stock.domain.value.StockName;
import com.khaledamin.ims.stock.domain.value.StockSKU;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "StockCreateRequest",
        description = "Stock item category request"
)
public class StockCreateRequest {

    @Schema(
            example = "Apple iPhone 17 Pro",
            description = "Mandatory stock item name",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = StockName.NULL_ERROR_MESSAGE)
    @NotBlank(message = StockName.NULL_ERROR_MESSAGE)
    @Pattern(regexp = StockName.PATTERN, message = StockName.PATTERN_ERROR_MESSAGE)
    @Size(max = StockName.MAX_LENGTH, message = StockName.MAX_LENGTH_ERROR_MESSAGE)
    private String name;


    @Schema(
            example = "IPHONE17PRO-BLK-256",
            description = "Organization unique SKU",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = StockSKU.NULL_ERROR_MESSAGE)
    @NotBlank(message = StockSKU.NULL_ERROR_MESSAGE)
    @Pattern(regexp = StockSKU.PATTERN, message = StockSKU.PATTERN_ERROR_MESSAGE)
    @Size(max = StockSKU.MAX_LENGTH, message = StockSKU.MAX_LENGTH_ERROR_MESSAGE)
    private String sku;

    @Schema(
            example = "Latest Apple flagship smartphone",
            description = "Optional stock item description",
            nullable = true
    )
    @Size(max = StockDescription.MAX_LENGTH, message = StockDescription.MAX_LENGTH_ERROR_MESSAGE)
    private String description;


    @Schema(
            description = "Optional stock item image file",
            type = "string",
            format = "binary"
    )
    private MultipartFile image;
}
