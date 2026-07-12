package com.khaledamin.ims.reservation.domain.value;

import com.khaledamin.ims.reservation.exception.ReservationValidationException;

import java.time.Instant;

public record ReservationExpirationDate(Instant value) {
    public static final String PAST_DATE_ERROR_MESSAGE = "Expiration date must not be in the past";

    public ReservationExpirationDate {
        validate(value);
    }

    private static void validate(Instant value) {

        if (value == null) {
            return;
        }

        if (value.isBefore(Instant.now())) {
            throw ReservationValidationException.invalidExpirationDate()
                    .withClientDetails("reason", PAST_DATE_ERROR_MESSAGE)
                    .withDebugDetails("receivedValue", value);
        }
    }

    public static ReservationExpirationDate of(Instant value) {
        return new ReservationExpirationDate(value);
    }
}
