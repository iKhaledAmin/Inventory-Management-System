package com.khaledamin.ims.reservation.exception;

import com.khaledamin.ims.core.exception.validation.ValidationError;
import com.khaledamin.ims.core.exception.validation.ValidationException;

public class ReservationValidationException extends ValidationException {
    // ----------------------------------- Constructors ----------------------------------- //

    protected ReservationValidationException(ValidationError error) {
        super(error);
    }

    // ----------------------------------- End Constructors ----------------------------------- //



    // ----------------------------------- Static Methods ----------------------------------- //


    public static ReservationValidationException invalidReceivedQuantity() {
        return new ReservationValidationException(ReservationValidationError.QUANTITY_INVALID);
    }

    public static ReservationValidationException invalidExpirationDate() {
        return new ReservationValidationException(ReservationValidationError.EXPIRATION_DATE_INVALID);
    }

    public static ReservationValidationException invalidCode() {
        return new ReservationValidationException(ReservationValidationError.CODE_INVALID);
    }


    // ----------------------------------- End Static Methods ----------------------------------- //
}
