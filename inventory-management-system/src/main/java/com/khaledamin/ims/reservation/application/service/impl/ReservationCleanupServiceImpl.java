package com.khaledamin.ims.reservation.application.service.impl;

import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.reservation.application.service.ReservationCleanupService;
import com.khaledamin.ims.reservation.domain.model.Reservation;
import com.khaledamin.ims.reservation.domain.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationCleanupServiceImpl implements ReservationCleanupService {

    private final ReservationRepository reservationRepository;
    private final BusinessEventLogger businessEventLogger;

    @Transactional
    @Override
    public void cleanupExpiredReservations() {

        List<Reservation> expiredReservations =
                reservationRepository.findExpiredActiveReservations(
                        Instant.now()
                );

        for (Reservation reservation : expiredReservations) {

            reservation.expire();

            reservationRepository.save(reservation);

            businessEventLogger.reservationExpired(
                    reservation.getCode(),
                    reservation.getOrganization().getCode()
            );
        }
    }
}