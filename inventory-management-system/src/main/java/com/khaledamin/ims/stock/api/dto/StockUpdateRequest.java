package com.khaledamin.ims.stock.api.dto;

import com.khaledamin.ims.stock.domain.value.StockDescription;
import com.khaledamin.ims.stock.domain.value.StockName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "StockUpdateRequest",
        description = "Stock item update request"
)
public class StockUpdateRequest {

    @Schema(
            example = "Apple iPhone 17 Pro Max",
            description = "Optional stock item name"
    )
    @Pattern(regexp = StockName.PATTERN, message = StockName.PATTERN_ERROR_MESSAGE)
    @Size(max = StockName.MAX_LENGTH, message = StockName.MAX_LENGTH_ERROR_MESSAGE)
    private String name;

    @Schema(
            example = "Updated product description",
            description = "Optional stock item description"
    )
    @Size(max = StockDescription.MAX_LENGTH, message = StockDescription.MAX_LENGTH_ERROR_MESSAGE)
    private String description;

    @Schema(
            description = "Optional replacement image",
            type = "string",
            format = "binary"
    )
    private MultipartFile image;
}