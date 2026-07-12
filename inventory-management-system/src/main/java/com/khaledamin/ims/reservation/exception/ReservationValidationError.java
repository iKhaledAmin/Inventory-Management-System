package com.khaledamin.ims.reservation.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationValidationError implements ValidationError {


    QUANTITY_INVALID(
            SystemDomain.RESERVATION,
            "RESERVATION_QUANTITY_INVALID",
            "Reservation quantity is invalid"
    ),

    EXPIRATION_DATE_INVALID(
            SystemDomain.RESERVATION,
            "RESERVATION_EXPIRATION_DATE_INVALID",
            "Reservation expiration date is invalid"
    ),

    CODE_INVALID(
            SystemDomain.RESERVATION,
            "RESERVATION_CODE_INVALID",
            "Reservation code is invalid"
    );
    private final SystemDomain domain;
    private final String code;
    private final String message;
}
