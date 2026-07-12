package com.khaledamin.ims.reservation.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationTechnicalError implements TechnicalError {


    ORGANIZATION_NULL(
            SystemDomain.RESERVATION,
            "RESERVATION_ORGANIZATION_NULL",
            "Reservation organization is null"
    ),


    QUANTITY_NULL(
            SystemDomain.RESERVATION,
            "RESERVATION_QUANTITY_NULL",
            "Reservation quantity is null"
    ),

    RESERVATION_NULL(
            SystemDomain.RESERVATION,
            "RESERVATION_NULL",
            "Reservation is null"
    ),

    STOCK_BATCH_NULL(
            SystemDomain.RESERVATION,
            "RESERVATION_STOCK_BATCH_NULL",
            "Reservation stock batch is null"
    ),


    STOCK_NULL(
            SystemDomain.RESERVATION,
            "RESERVATION_STOCK_NULL",
            "Reservation stock is null"
    ),

    ITEM_NULL(
            SystemDomain.RESERVATION,
            "RESERVATION_ITEM_NULL",
            "Reservation item is null"
    ),

    EXPIRATION_DATE_NULL(
            SystemDomain.RESERVATION,
            "RESERVATION_EXPIRATION_DATE_NULL",
            "Reservation expiration date is null"
    );



    private final SystemDomain domain;
    private final String code;
    private final String message;
}
