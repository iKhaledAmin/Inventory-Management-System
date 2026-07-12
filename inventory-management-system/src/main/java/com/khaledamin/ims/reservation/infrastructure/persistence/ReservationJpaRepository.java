package com.khaledamin.ims.reservation.infrastructure.persistence;

import com.khaledamin.ims.core.persistence.BaseRepository;
import com.khaledamin.ims.reservation.domain.model.Reservation;
import com.khaledamin.ims.reservation.domain.model.ReservationStatus;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends BaseRepository<Reservation,Long> {
    Optional<Reservation> findByCode(String code);

    List<Reservation> findByStatusAndExpiresAtBefore(ReservationStatus status, Instant expiresAt);
}
