package com.khaledamin.ims.reservation.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.documentation.examples.UnauthorizedExamples;
import com.khaledamin.ims.core.api.response.ApiActionResponse;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.reservation.api.documentation.examples.ReservationConfirmExamples;
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
        summary = "Confirm Reservation",
        description = """
        Confirms an existing reservation and consumes all reserved stock quantities.

        This endpoint is primarily used by external inventory clients
        such as E-Commerce systems after successful payment or order completion.

        Required Authority:
        - reservation_confirm

        Business Rules:
        - Reservation must exist.
        - Reservation must belong to caller organization.
        - Reservation must not be expired.
        - Reservation must not already be confirmed.
        - Reservation must not already be released.
        - Confirming a reservation permanently consumes reserved stock.
        - This operation cannot be reversed.
        """
)

@ApiResponse(
        responseCode = "200",
        description = "Reservation confirmed successfully",
        content = @Content(
                schema = @Schema(
                        implementation = ApiActionResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Reservation Confirmed",
                                summary = "Reserved stock consumed successfully",
                                value = ReservationConfirmExamples.RESERVATION_CONFIRMED
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
                                value = ReservationConfirmExamples.INVALID_RESERVATION_CODE
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
                                value = ReservationConfirmExamples.RESERVATION_NOT_FOUND
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
                                value = ReservationConfirmExamples.ALREADY_CONFIRMED
                        ),

                        @ExampleObject(
                                name = "Already Released",
                                summary = "Reservation was already released",
                                value = ReservationConfirmExamples.ALREADY_RELEASED
                        ),

                        @ExampleObject(
                                name = "Already Expired",
                                summary = "Reservation expired before confirmation",
                                value = ReservationConfirmExamples.ALREADY_EXPIRED
                        )
                }
        )
)

@ApiResponse(
        responseCode = "403",
        description = "Access denied or confirmation forbidden",
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
                                name = "Confirmation Forbidden",
                                summary = "Business policy prevents confirming this reservation",
                                value = ReservationConfirmExamples.CONFIRMATION_FORBIDDEN
                        )
                }
        )
)

@UnauthenticatedApiDocs
@InternalServerErrorApiDocs
public @interface ReservationConfirmApiDocs {
}