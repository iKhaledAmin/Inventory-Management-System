package com.khaledamin.ims.reservation.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.business.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReservationBusinessError implements BusinessError {


    NOT_FOUND(
            SystemDomain.RESERVATION,
            "RESERVATION_NOT_FOUND",
            HttpStatus.NOT_FOUND,
            "Reservation not found"
    ),

    ALREADY_EXPIRED(
            SystemDomain.RESERVATION,
            "RESERVATION_ALREADY_EXPIRED",
            HttpStatus.CONFLICT,
            "Reservation is already expired"
    ),

    ALREADY_CONFIRMED(
            SystemDomain.RESERVATION,
            "RESERVATION_ALREADY_CONFIRMED",
            HttpStatus.CONFLICT,
            "Reservation is already confirmed"
    ),

    ALREADY_RELEASED(
            SystemDomain.RESERVATION,
            "RESERVATION_ALREADY_RELEASED",
            HttpStatus.CONFLICT,
            "Reservation is already released"
    );
    private final SystemDomain domain;
    private final String code;
    private final HttpStatus status;
    private final String message;
}
