package com.khaledamin.ims.stock.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.stock.api.documentation.examples.StockExistenceExamples;
import com.khaledamin.ims.stock.api.documentation.schema.StockExistenceApiSchema;
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
        summary = "Check Stock Existence",
        description = """
        Lightweight validation endpoint used by external systems (e-commerce) to verify
        whether a stock item exists in the Inventory Management System.

        This endpoint:

        - Does NOT return stock details
        - Does NOT expose inventory quantities
        - Is optimized for cross-system validation
        - Is intended for machine-to-machine communication

        Business Rules:
        - Stock must belong to the caller's organization context
        - Only existence is validated (no availability or quantity logic)
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Stock existence checked successfully",
        content = @Content(
                schema = @Schema(
                        implementation = StockExistenceApiSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Stock Exists",
                                summary = "Stock item exists in IMS",
                                value = StockExistenceExamples.STOCK_EXISTS
                        ),
                        @ExampleObject(
                                name = "Stock Does Not Exist",
                                summary = "Stock item not found in IMS",
                                value = StockExistenceExamples.STOCK_NOT_EXISTS
                        )
                }
        )
)

@UnauthenticatedApiDocs
@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface StockExistenceApiDocs {
}