package com.khaledamin.ims.reservation.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.documentation.examples.UnauthorizedExamples;
import com.khaledamin.ims.core.api.response.ApiActionResponse;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.reservation.api.documentation.examples.ReservationReleaseExamples;
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
        summary = "Release Reservation",
        description = """
        Releases an existing reservation and returns all reserved quantities
        back to available stock.

        This endpoint is primarily used by external inventory clients
        such as E-Commerce systems when checkout, payment,
        or order creation fails after reservation.

        Required Authority:
        - reservation_release

        Business Rules:
        - Reservation must exist.
        - Reservation must belong to caller organization.
        - Reservation must not already be confirmed.
        - Reservation must not already be released.
        - Releasing a reservation returns reserved quantities to inventory.
        - Expired reservations are treated as already released and the operation is effectively idempotent.
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Reservation released successfully",
        content = @Content(
                schema = @Schema(
                        implementation = ApiActionResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Reservation Released",
                                summary = "Reserved stock returned successfully",
                                value = ReservationReleaseExamples.RESERVATION_RELEASED
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
                                name = "Invalid Reservation Code",
                                summary = "Reservation code violates validation rules",
                                value = ReservationReleaseExamples.INVALID_RESERVATION_CODE
                        )
                }
        )
)

@ApiResponse(
        responseCode = "404",
        description = "Reservation not found",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Reservation Not Found",
                                summary = "Requested reservation does not exist",
                                value = ReservationReleaseExamples.RESERVATION_NOT_FOUND
                        )
                }
        )
)

@ApiResponse(
        responseCode = "409",
        description = "Reservation state conflict",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Already Confirmed",
                                summary = "Reservation was already confirmed",
                                value = ReservationReleaseExamples.ALREADY_CONFIRMED
                        ),

                        @ExampleObject(
                                name = "Already Released",
                                summary = "Reservation was already released",
                                value = ReservationReleaseExamples.ALREADY_RELEASED
                        )
                }
        )
)

@ApiResponse(
        responseCode = "403",
        description = "Access denied or release forbidden",
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
                                name = "Release Forbidden",
                                summary = "Business policy prevents releasing this reservation",
                                value = ReservationReleaseExamples.RELEASE_FORBIDDEN
                        )
                }
        )
)

@UnauthenticatedApiDocs
@InternalServerErrorApiDocs
public @interface ReservationReleaseApiDocs {
}