package com.khaledamin.ims.reservation.application.service;

import com.khaledamin.ims.reservation.domain.model.Reservation;
import com.khaledamin.ims.reservation.domain.value.ReservationCode;

import java.util.Optional;

public interface ReservationQueryService {

    Optional<Reservation> getOptionalByCode(ReservationCode code);
    Reservation getByCode(ReservationCode code);
}
