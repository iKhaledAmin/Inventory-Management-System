package com.khaledamin.ims.stock.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.documentation.examples.UnauthorizedExamples;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.stock.api.documentation.examples.StockViewExamples;
import com.khaledamin.ims.stock.api.documentation.schema.StockApiResponseSchema;
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
        summary = "View Stock Item",
        description = """
        Retrieves a stock item by its code.

        Required Authority:
        - stock_read

        Business Rules:
        - Stock item must exist.
        - User must have access to the stock item's organization.
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Stock item retrieved successfully",
        content = @Content(
                schema = @Schema(
                        implementation = StockApiResponseSchema.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Minimal Response",
                                summary = "Stock item without image",
                                value = StockViewExamples.SUCCESS_SHORT_RESPONSE
                        ),

                        @ExampleObject(
                                name = "Full Response",
                                summary = "Stock item with image",
                                value = StockViewExamples.SUCCESS_FULL_RESPONSE
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
                                value = StockViewExamples.NOT_FOUND
                        )
                }
        )
)

@ApiResponse(
        responseCode = "403",
        description = "Forbidden",
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
                                name = "Stock View Forbidden",
                                summary = "Business policy prevents viewing this stock item",
                                value = StockViewExamples.FORBIDDEN
                        )
                }
        )
)

@UnauthenticatedApiDocs
@InternalServerErrorApiDocs
public @interface StockViewApiDocs {
}