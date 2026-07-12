package com.khaledamin.ims.reservation.exception;

import com.khaledamin.ims.core.exception.policy.PolicyError;
import com.khaledamin.ims.core.exception.policy.PolicyException;

public class ReservationPolicyException extends PolicyException {
    // ----------------------------------- Constructors ----------------------------------- //

    protected ReservationPolicyException(PolicyError error) {
        super(error);
    }

    // ----------------------------------- End Constructors ----------------------------------- //

    // ----------------------------------- Static Methods ----------------------------------- //

    public static ReservationPolicyException forbiddenReservation() {
        return new ReservationPolicyException(ReservationPolicyError.RESERVATION_FORBIDDEN);
    }

    public static ReservationPolicyException forbiddenRelease() {
        return new ReservationPolicyException(ReservationPolicyError.RELEASE_FORBIDDEN);
    }

    public static ReservationPolicyException forbiddenConfirmation() {
        return new ReservationPolicyException(ReservationPolicyError.CONFIRMATION_FORBIDDEN);
    }

    // ----------------------------------- End Static Methods ----------------------------------- //
}
