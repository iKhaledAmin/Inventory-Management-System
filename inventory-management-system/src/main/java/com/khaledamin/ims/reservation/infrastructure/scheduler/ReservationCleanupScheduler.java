package com.khaledamin.ims.reservation.infrastructure.scheduler;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalException;
import com.khaledamin.ims.core.logging.definition.SystemOperation;
import com.khaledamin.ims.core.logging.definition.SystemOperationType;
import com.khaledamin.ims.core.logging.event.ExceptionLogger;
import com.khaledamin.ims.core.logging.event.SystemOperationLogger;
import com.khaledamin.ims.reservation.application.service.ReservationCleanupService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationCleanupScheduler {

    private final ReservationCleanupService cleanupService;
    private final ExceptionLogger exceptionLogger;
    private final SystemOperationLogger systemOperationLogger;

    @Scheduled(
            fixedDelayString = "#{${application.reservation.scheduler.interval-seconds} * 1000}"
    )
    public void cleanupExpiredReservations() {

        systemOperationLogger.started(
                SystemOperation.RESERVATION_CLEANUP_JOB,
                SystemOperationType.SCHEDULED,
                SystemDomain.RESERVATION
        );

        try {

            cleanupService.cleanupExpiredReservations();

            systemOperationLogger.completed(
                    SystemOperation.RESERVATION_CLEANUP_JOB,
                    SystemOperationType.SCHEDULED,
                    SystemDomain.RESERVATION
            );

        } catch (TechnicalException ex) {

            exceptionLogger.log(ex);

            systemOperationLogger.failed(
                    SystemOperation.RESERVATION_CLEANUP_JOB,
                    SystemOperationType.SCHEDULED,
                    SystemDomain.RESERVATION,
                    ex
            );
        }
    }
}