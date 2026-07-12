package com.khaledamin.ims.reservation.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.policy.PolicyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationPolicyError implements PolicyError {


    RESERVATION_FORBIDDEN(
            SystemDomain.RESERVATION,
            "RESERVATION_FORBIDDEN",
            "Reserve stock is forbidden"
    ),

    RELEASE_FORBIDDEN(
            SystemDomain.RESERVATION,
            "RESERVATION_RELEASE_FORBIDDEN",
            "Release stock reservation is forbidden"
    ),

    CONFIRMATION_FORBIDDEN(
            SystemDomain.RESERVATION,
            "RESERVATION_CONFIRMATION_FORBIDDEN",
            "Confirm stock reservation is forbidden"
    );
    private final SystemDomain domain;
    private final String code;
    private final String message;
}
