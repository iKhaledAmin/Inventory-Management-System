package com.khaledamin.ims.stock.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.documentation.examples.UnauthorizedExamples;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.stock.api.documentation.examples.StockListBatchExamples;
import com.khaledamin.ims.stock.api.documentation.schema.StockBatchApiPageResponseSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

@Operation(
        summary = "List Stock Batches",
        description = """
        Returns a paginated list of batches belonging to a stock item.

        Required Authority:
        - stock_read_batches

        Features:
        - Pagination support
        - Sorting support
        - Filtering support

        Supported Filters:
        - filter.hasStock
        - filter.expired
        - filter.expirationBefore

        Business Rules:
        - Stock item must exist.
        - User must own the stock item's organization.
        - Page number must be greater than or equal to 0.
        - Page size must be between 1 and 100.
        - Sort field must be one of the supported values.
        - Sort direction must be ASC or DESC.

        Default Values:
        - page = 0
        - size = 20
        - sortBy = RECEIVED_DATE
        - direction = DESC

        Supported Sort Fields:
        - RECEIVED_DATE
        - EXPIRATION_DATE
        - RECEIVED_QUANTITY
        - AVAILABLE_QUANTITY
        - UNIT_COST

        Example Filters:

        ?filter.hasStock=true

        ?filter.expired=true

        ?filter.expirationBefore=2027-01-01

        Filters may be combined.
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Stock batches retrieved successfully",
        content = @Content(
                schema = @Schema(
                        implementation = StockBatchApiPageResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Paginated Batch List",
                                summary = "Successful batch listing",
                                value = StockListBatchExamples.SUCCESS_RESPONSE
                        )
                }
        )
)

@ApiResponse(
        responseCode = "400",
        description = "Validation failed",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Invalid Page Number",
                                summary = "Page number violates validation rules",
                                value = StockListBatchExamples.INVALID_PAGE_NUMBER
                        ),

                        @ExampleObject(
                                name = "Invalid Page Size",
                                summary = "Page size violates validation rules",
                                value = StockListBatchExamples.INVALID_PAGE_SIZE
                        ),

                        @ExampleObject(
                                name = "Invalid Sort Field",
                                summary = "Sort field is not supported",
                                value = StockListBatchExamples.INVALID_SORT_FIELD
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = StockListBatchExamples.MULTIPLE_VALIDATION_ERRORS
                        )
                }
        )
)

@ApiResponse(
        responseCode = "403",
        description = "Listing stock batches is forbidden",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Access Denied",
                                summary = "Authenticated user does not have required authority",
                                value = UnauthorizedExamples.FORBIDDEN
                        ),
                        @ExampleObject(
                                name = "List Batches Forbidden",
                                summary = "Current user does not own the stock organization",
                                value = StockListBatchExamples.FORBIDDEN
                        )

                }
        )
)

@ApiResponse(
        responseCode = "404",
        description = "Stock item not found",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Stock Not Found",
                                summary = "Stock does not exist",
                                value = StockListBatchExamples.STOCK_NOT_FOUND
                        )
                }
        )
)

@UnauthenticatedApiDocs
@InternalServerErrorApiDocs
public @interface StockListBatchesApiDocs {
}