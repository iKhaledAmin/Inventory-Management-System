package com.khaledamin.ims.reservation.application.service.impl;

import com.khaledamin.ims.reservation.application.service.ReservationQueryService;
import com.khaledamin.ims.reservation.domain.model.Reservation;
import com.khaledamin.ims.reservation.domain.repository.ReservationRepository;
import com.khaledamin.ims.reservation.domain.value.ReservationCode;
import com.khaledamin.ims.reservation.exception.ReservationBusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationQueryServiceImpl implements ReservationQueryService {

    private final ReservationRepository reservationRepository;

    @Override
    public Optional<Reservation> getOptionalByCode(ReservationCode code) {
        return reservationRepository.findByCode(code.toString());
    }

    @Override
    public Reservation getByCode(ReservationCode code) {
        return getOptionalByCode(code)
                .orElseThrow(() -> ReservationBusinessException.notFound()
                        .withDebugDetails("reason", "Reservation not found")
                        .withDebugDetails("code", code.toString())
                );
    }
}
