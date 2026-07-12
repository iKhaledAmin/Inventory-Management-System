package com.khaledamin.ims.reservation.application.service;

import com.khaledamin.ims.reservation.api.dto.ReservationRequest;
import com.khaledamin.ims.reservation.api.dto.ReservationResponse;
import com.khaledamin.ims.reservation.domain.value.ReservationCode;

public interface ReservationManagementService {

    ReservationResponse reserveStocks(ReservationRequest request);

    void releaseReservation(ReservationCode reservationCode);

    void confirmReservation(ReservationCode reservationCode);
}
