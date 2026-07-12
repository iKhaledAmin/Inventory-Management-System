package com.khaledamin.ims.reservation.exception;

import com.khaledamin.ims.core.exception.business.BusinessError;
import com.khaledamin.ims.core.exception.business.BusinessException;

public class ReservationBusinessException extends BusinessException {
    // ----------------------------------- Constructors ----------------------------------- //
    protected ReservationBusinessException(BusinessError error) {
        super(error);
    }

    // ----------------------------------- End Constructors ----------------------------------- //


    // ----------------------------------- Static Methods ----------------------------------- //


    public static ReservationBusinessException notFound() {
        return new ReservationBusinessException(ReservationBusinessError.NOT_FOUND);
    }

    public static ReservationBusinessException alreadyExpired() {
        return new ReservationBusinessException(ReservationBusinessError.ALREADY_EXPIRED);
    }

    public static ReservationBusinessException alreadyConfirmed() {
        return new ReservationBusinessException(ReservationBusinessError.ALREADY_CONFIRMED);
    }

    public static ReservationBusinessException alreadyReleased() {
        return new ReservationBusinessException(ReservationBusinessError.ALREADY_RELEASED);
    }

    // ----------------------------------- End Static Methods ----------------------------------- //
}
