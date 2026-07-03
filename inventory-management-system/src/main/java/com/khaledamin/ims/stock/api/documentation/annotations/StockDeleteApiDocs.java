package com.khaledamin.ims.stock.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.documentation.examples.ForbiddenExamples;
import com.khaledamin.ims.core.api.response.ApiActionResponse;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.stock.api.documentation.examples.StockDeleteExamples;
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
        summary = "Delete Stock Item",
        description = """
        Soft deletes an existing stock item.

        Required Authority:
        - stock_delete

        Business Rules:
        - Stock item is not physically removed
        - Stock item status becomes INACTIVE
        - Historical references remain intact
        - Stock item cannot be deleted when it contains available stock batches.
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Stock item deleted successfully",
        content = @Content(
                schema = @Schema(
                        implementation = ApiActionResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Stock Deleted",
                                summary = "Stock item deleted successfully",
                                value = StockDeleteExamples.STOCK_DELETED
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
                                summary = "Requested stock item does not exist",
                                value = StockDeleteExamples.NOT_FOUND
                        )
                }
        )
)

@ApiResponse(
        responseCode = "409",
        description = "Delete operation not allowed",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Delete Not Allowed",
                                summary = "Stock item contains available stock batches",
                                value = StockDeleteExamples.NOT_ALLOWED
                        )
                }
        )
)

@ApiResponse(
        responseCode = "403",
        description = "Access denied or delete operation forbidden",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Access Denied",
                                summary = "Authenticated user does not have required authority",
                                value = ForbiddenExamples.FORBIDDEN
                        ),

                        @ExampleObject(
                                name = "Stock Delete Forbidden",
                                summary = "Business policy prevents deleting this stock item",
                                value = StockDeleteExamples.FORBIDDEN
                        )
                }
        )
)

@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface StockDeleteApiDocs {
}