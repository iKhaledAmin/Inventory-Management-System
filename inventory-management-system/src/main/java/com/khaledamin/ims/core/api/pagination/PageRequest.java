package com.khaledamin.ims.core.api.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;



/**
 * Utility responsible for transforming paginated content from one type
 * into another while preserving all pagination metadata.
 *
 * <p>
 * Commonly used when converting domain entities into API response DTOs.
 * </p>
 *
 * <h2>Example</h2>
 *
 * <pre>{@code
 * PageResult<Account> accounts = accountService.getAll(request);
 *
 * PageResult<AccountResponse> response =
 *         PageMapper.map(accounts, accountMapper::toResponse);
 * }</pre>
 *
 * <p>
 * All pagination information such as page number, size,
 * total elements, total pages, and navigation flags
 * are copied automatically.
 * </p>
 *
 * @see PageResult
 */


@Setter
public abstract class PageRequest {
    @Schema(
            description = "Page number (0-based)",
            example = "0"
    )
    @Getter
    @Min(value = 0)
    private int page = 0;

    @Schema(
            description = "Page size",
            example = "20"
    )
    @Getter
    @Min(value = 1)
    @Max(value = 100)
    private int size = 20;

    @Schema(
            description = "Sort direction",
            allowableValues = {
                    "ASC",
                    "DESC"
            },
            example = "DESC"
    )
     private String direction = SortDirection.getDefault();

    public String getDirection() {
        return SortDirection.getFrom(direction);
    }

     public abstract String getSortBy();

}
