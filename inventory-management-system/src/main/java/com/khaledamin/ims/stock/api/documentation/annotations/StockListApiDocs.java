package com.khaledamin.ims.stock.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.stock.api.documentation.examples.StockListExamples;
import com.khaledamin.ims.stock.api.documentation.schema.StockApiPageResponseSchema;
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
        summary = "List Stock Items",
        description = """
        Returns a paginated list of stock items belonging to the current organization.

        Required Authority:
        - stock_read
        
        Features:
        - Pagination support
        - Sorting support

        Business Rules:
        - Page number must be greater than or equal to 0.
        - Page size must be between 1 and 100.
        - Sort field must be one of the supported values.
        - Sort direction must be ASC or DESC.

        Default Values:
        - page = 0
        - size = 20
        - sortBy = NAME
        - direction = DESC

        Response Characteristics:
        - Lightweight response optimized for listing screens.
        - Does not include stock calculations.
        - Includes only a single image variant.
        - Supports pagination and sorting.
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Stock items retrieved successfully",
        content = @Content(
                schema = @Schema(
                        implementation = StockApiPageResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Paginated Stock List",
                                summary = "Successful stock listing",
                                value = StockListExamples.SUCCESS_RESPONSE
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
                                value = StockListExamples.INVALID_PAGE_NUMBER
                        ),

                        @ExampleObject(
                                name = "Invalid Page Size",
                                summary = "Page size violates validation rules",
                                value = StockListExamples.INVALID_PAGE_SIZE
                        ),

                        @ExampleObject(
                                name = "Invalid Sort Field",
                                summary = "Sort field is not supported",
                                value = StockListExamples.INVALID_SORT_FIELD
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = StockListExamples.MULTIPLE_VALIDATION_ERRORS
                        )
                }
        )
)

@UnauthenticatedApiDocs
@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface StockListApiDocs {
}