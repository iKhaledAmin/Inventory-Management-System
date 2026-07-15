package com.khaledamin.ims.reservation.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.documentation.examples.UnauthorizedExamples;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.reservation.api.documentation.examples.ReservationCreateExamples;
import com.khaledamin.ims.reservation.api.documentation.schema.ReservationApiResponseSchema;
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
        summary = "Reserve Stocks",
        description = """
        Creates a stock reservation.

        This endpoint is primarily used by external inventory clients
        such as E-Commerce systems during checkout workflows.

        Required Authority:
        - reservation_create

        Business Rules:
        - All requested stock items must belong to the caller organization.
        - Allocation strategy is determined by organization settings.
        - Reservation expiration is determined by organization settings.
        - Reservation succeeds only when all requested quantities are reservable.
        - When stock is unavailable the operation returns success=false
          with unavailable item details.
        - Reserved stock remains locked until:
            • Reservation confirmation
            • Reservation release
            • Reservation expiration
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Reservation processed successfully",
        content = @Content(
                schema = @Schema(
                        implementation = ReservationApiResponseSchema.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Reservation Created",
                                summary = "Reservation created successfully",
                                value = ReservationCreateExamples.RESERVATION_CREATED
                        ),

                        @ExampleObject(
                                name = "Insufficient Stock",
                                summary = "Reservation could not be created due to unavailable stock quantities",
                                value = ReservationCreateExamples.INSUFFICIENT_STOCK
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
                                name = "Invalid Quantity",
                                summary = "Quantity must be greater than zero",
                                value = ReservationCreateExamples.INVALID_QUANTITY
                        ),

                        @ExampleObject(
                                name = "Null Quantity",
                                summary = "Quantity must not be null",
                                value = ReservationCreateExamples.NULL_QUANTITY
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = ReservationCreateExamples.MULTIPLE_VALIDATION_ERRORS
                        )
                }
        )
)

@ApiResponse(
        responseCode = "403",
        description = "Access denied or reservation forbidden",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Access Denied",
                                summary = "Authenticated actor does not have required authority",
                                value = UnauthorizedExamples.FORBIDDEN
                        ),

                        @ExampleObject(
                                name = "Reservation Forbidden",
                                summary = "Business policy prevents reserving stock",
                                value = ReservationCreateExamples.RESERVATION_FORBIDDEN
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
                                value = ReservationCreateExamples.STOCK_NOT_FOUND
                        )
                }
        )
)

@UnauthenticatedApiDocs
@InternalServerErrorApiDocs
public @interface ReservationCreateApiDocs {
}