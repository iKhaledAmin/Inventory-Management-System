package com.khaledamin.ims.reservation.domain.value;

import com.khaledamin.ims.reservation.exception.ReservationValidationException;

import java.util.Locale;

public record ReservationCode(String value) {

    public static final String NULL_ERROR_MESSAGE = "Reservation code must not be null or empty";

    public ReservationCode {
        value = normalize(value);
        validate(value);
    }


    private static String normalize(String value) {
        return value == null ? null : value.trim().toUpperCase(Locale.ROOT);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank()) {
            throw ReservationValidationException.invalidCode()
                    .withClientDetails("reason", NULL_ERROR_MESSAGE)
                    .withDebugDetails("reservationCode", value);
        }
    }

    public static ReservationCode of(String value) {
        return new ReservationCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
