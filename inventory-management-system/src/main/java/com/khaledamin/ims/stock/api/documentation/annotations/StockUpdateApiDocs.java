package com.khaledamin.ims.stock.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.documentation.examples.ForbiddenExamples;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.stock.api.documentation.examples.StockUpdateExamples;
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
        summary = "Update Stock Item",
        description = """
        Updates an existing stock item.

        Required Authority:
        - stock_update

        Consumes:
        - multipart/form-data

        Business Rules:
        - Stock item must exist.

        Update Behavior:
        - All fields are optional.
        - Only provided fields are updated.
        - Omitted fields remain unchanged.

        Updatable Fields:
        - name
        - description
        - image
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Stock item updated successfully",
        content = @Content(
                schema = @Schema(
                        implementation = StockApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Minimal Response",
                                summary = "Stock updated without image changes",
                                value = StockUpdateExamples.SUCCESS_SHORT_RESPONSE
                        ),
                        @ExampleObject(
                                name = "Full Response",
                                summary = "Stock updated with image",
                                value = StockUpdateExamples.SUCCESS_FULL_RESPONSE
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
                                name = "Invalid Name",
                                summary = "Stock name violates validation rules",
                                value = StockUpdateExamples.INVALID_NAME
                        ),

                        @ExampleObject(
                                name = "Invalid Description",
                                summary = "Stock description violates validation rules",
                                value = StockUpdateExamples.INVALID_DESCRIPTION
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = StockUpdateExamples.MULTIPLE_VALIDATION_ERRORS
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
                                value = StockUpdateExamples.NOT_FOUND
                        )
                }
        )
)

@ApiResponse(
        responseCode = "403",
        description = "Access denied or update operation forbidden",
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
                                name = "Stock Update Forbidden",
                                summary = "Business policy prevents updating this stock item",
                                value = StockUpdateExamples.FORBIDDEN
                        )
                }
        )
)

@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface StockUpdateApiDocs {
}