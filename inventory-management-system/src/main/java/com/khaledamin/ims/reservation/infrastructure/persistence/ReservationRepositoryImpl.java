package com.khaledamin.ims.reservation.infrastructure.persistence;

import com.khaledamin.ims.reservation.domain.model.Reservation;
import com.khaledamin.ims.reservation.domain.model.ReservationStatus;
import com.khaledamin.ims.reservation.domain.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository jpaRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return jpaRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findByCode(String code) {
        return jpaRepository.findByCode(code);
    }

    @Override
    public List<Reservation> findExpiredActiveReservations(Instant now) {

        return jpaRepository.findByStatusAndExpiresAtBefore(
                ReservationStatus.ACTIVE,
                now
        );
    }

}
