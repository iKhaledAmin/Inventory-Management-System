package com.khaledamin.ims.reservation.domain.repository;

import com.khaledamin.ims.reservation.domain.model.Reservation;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Optional<Reservation> findByCode(String code);

    List<Reservation> findExpiredActiveReservations(Instant now);
}