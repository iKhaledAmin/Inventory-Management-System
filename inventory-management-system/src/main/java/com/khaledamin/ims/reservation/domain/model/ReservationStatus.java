package com.khaledamin.ims.reservation.domain.model;

public enum ReservationStatus {
    ACTIVE,     // still lock the stock quantity for specific customer
    CONFIRMED,  // successfully path (reserved then confirmed)
    RELEASED,   // something failed in the client(e-commerce system ,... ) side so he called our release API
    EXPIRED     // no action was taken by the client(e-commerce system ,... ) side so our schedule job released the reservation

    ;
    public static ReservationStatus getDefaultStatus() {
        return ReservationStatus.ACTIVE;
    }
}
