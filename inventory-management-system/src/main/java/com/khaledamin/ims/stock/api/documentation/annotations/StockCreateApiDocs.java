package com.khaledamin.ims.stock.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.stock.api.documentation.examples.StockCreateExamples;
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
        summary = "Create Stock Item",
        description = """
        Creates a new stock item.

        Required Authority:
        - stock_create

        Consumes:
        - multipart/form-data

        Business Rules:
        - SKU must be unique.
        - Image is optional.
        - Stock is created in ACTIVE status.
        """
)

@ApiResponse(
        responseCode = "201",
        description = "Stock item created successfully",
        content = @Content(
                schema = @Schema(
                        implementation = StockApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Minimal Response",
                                summary = "Stock created without image",
                                value = StockCreateExamples.SUCCESS_SHORT_RESPONSE
                        ),
                        @ExampleObject(
                                name = "Full Response",
                                summary = "Stock created with image",
                                value = StockCreateExamples.SUCCESS_FULL_RESPONSE
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
                                value = StockCreateExamples.INVALID_NAME
                        ),

                        @ExampleObject(
                                name = "Invalid SKU",
                                summary = "Stock SKU violates validation rules",
                                value = StockCreateExamples.INVALID_SKU
                        ),

                        @ExampleObject(
                                name = "Invalid Description",
                                summary = "Stock description violates validation rules",
                                value = StockCreateExamples.INVALID_DESCRIPTION
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = StockCreateExamples.MULTIPLE_VALIDATION_ERRORS
                        )
                }
        )
)

@ApiResponse(
        responseCode = "409",
        description = "Business rule violation",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "SKU Already Exists",
                                summary = "Stock SKU must be unique",
                                value = StockCreateExamples.SKU_ALREADY_EXISTS
                        )
                }
        )
)

@UnauthenticatedApiDocs
@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface StockCreateApiDocs {
}