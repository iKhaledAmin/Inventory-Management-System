package com.khaledamin.ims.reservation.domain.value;

import com.khaledamin.ims.reservation.exception.ReservationValidationException;


public record ReservationQuantity(Long value) {

    public static final String NULL_ERROR_MESSAGE = "Reservation quantity must not be null";

    public static final String SIZE_ERROR_MESSAGE = "Reservation quantity must be greater than zero";

    public ReservationQuantity {
        validate(value);
    }

    private static void validate(Long value) {

        if (value == null) {
            throw ReservationValidationException.invalidReceivedQuantity()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE);
        }

        if (value <= 0) {
            throw ReservationValidationException.invalidReceivedQuantity()
                    .withClientDetails("reason", SIZE_ERROR_MESSAGE)
                    .withDebugDetails("receivedValue", value);
        }
    }

    public static ReservationQuantity of(Long value) {
        return new ReservationQuantity(value);
    }

}
