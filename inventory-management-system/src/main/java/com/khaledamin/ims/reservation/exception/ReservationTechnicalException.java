package com.khaledamin.ims.reservation.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalError;
import com.khaledamin.ims.core.exception.technical.TechnicalException;

public class ReservationTechnicalException extends TechnicalException {
    // ----------------------------------- Constructors ----------------------------------- //
    protected ReservationTechnicalException(TechnicalError error) {
        super(error);
    }

    // ----------------------------------- End Constructors ----------------------------------- //

    // ----------------------------------- Static Methods ----------------------------------- //


    public static ReservationTechnicalException nullOrganization() {
        return new ReservationTechnicalException(ReservationTechnicalError.ORGANIZATION_NULL);
    }

    public static ReservationTechnicalException nullQuantity() {
        return new ReservationTechnicalException(ReservationTechnicalError.QUANTITY_NULL);
    }

    public static ReservationTechnicalException nullReservation() {
        return new ReservationTechnicalException(ReservationTechnicalError.RESERVATION_NULL);
    }

    public static ReservationTechnicalException nullStockBatch() {
        return new ReservationTechnicalException(ReservationTechnicalError.STOCK_BATCH_NULL);
    }


    public static ReservationTechnicalException nullStock() {
        return new ReservationTechnicalException(ReservationTechnicalError.STOCK_NULL);
    }

    public static ReservationTechnicalException nullReservationItem() {
        return new ReservationTechnicalException(ReservationTechnicalError.ITEM_NULL);
    }

    public static ReservationTechnicalException nullExpirationDate() {
        return new ReservationTechnicalException(ReservationTechnicalError.EXPIRATION_DATE_NULL);
    }

    // ----------------------------------- End Static Methods ----------------------------------- //
}
