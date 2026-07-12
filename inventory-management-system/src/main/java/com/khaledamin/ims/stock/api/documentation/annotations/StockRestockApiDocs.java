package com.khaledamin.ims.stock.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.documentation.examples.UnauthorizedExamples;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.stock.api.documentation.examples.StockRestockExamples;
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
        summary = "Restock Stock Item",
        description = """
        Adds inventory to an existing stock item.

        Required Authority:
        - stock_restock

        Business Rules:
        - Stock item must exist.
        - Quantity must be greater than zero.
        - Unit cost must be greater than zero.
        - User must have access to the stock item's organization.
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Stock item restocked successfully",
        content = @Content(
                schema = @Schema(
                        implementation = StockApiResponseSchema.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Minimal Response",
                                summary = "Stock item restocked without image",
                                value = StockRestockExamples.SUCCESS_SHORT_RESPONSE
                        ),

                        @ExampleObject(
                                name = "Full Response",
                                summary = "Stock item restocked with image",
                                value = StockRestockExamples.SUCCESS_FULL_RESPONSE
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
                                name = "Invalid Received Quantity",
                                summary = "Received quantity violates validation rules",
                                value = StockRestockExamples.INVALID_RECEIVED_QUANTITY
                        ),

                        @ExampleObject(
                                name = "Invalid Unit Cost",
                                summary = "Unit cost violates validation rules",
                                value = StockRestockExamples.INVALID_UNIT_COST
                        ),

                        @ExampleObject(
                                name = "Invalid Expiration Date",
                                summary = "Expiration date violates validation rules",
                                value = StockRestockExamples.INVALID_EXPIRATION_DATE
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = StockRestockExamples.MULTIPLE_VALIDATION_ERRORS
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
                                value = StockRestockExamples.NOT_FOUND
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
                                name = "Stock Restock Forbidden",
                                summary = "Business policy prevents restocking this stock item",
                                value = StockRestockExamples.FORBIDDEN
                        )
                }
        )
)

@UnauthenticatedApiDocs
@InternalServerErrorApiDocs
public @interface StockRestockApiDocs {
}