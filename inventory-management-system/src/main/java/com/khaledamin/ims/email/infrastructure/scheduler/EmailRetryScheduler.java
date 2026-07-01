package com.khaledamin.ims.email.infrastructure.scheduler;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalException;
import com.khaledamin.ims.core.logging.event.ExceptionLogger;
import com.khaledamin.ims.core.logging.event.SystemOperationLogger;
import com.khaledamin.ims.core.logging.definition.SystemOperation;
import com.khaledamin.ims.core.logging.definition.SystemOperationType;
import com.khaledamin.ims.email.application.port.in.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class EmailRetryScheduler {
    private final EmailService emailService;
    private final ExceptionLogger exceptionLogger;
    private final SystemOperationLogger systemOperationLogger;

    @Scheduled(fixedDelayString = "#{${application.email.retry.scheduler.interval-seconds} * 1000}") // Run every interval seconds
    public void retryFailedEmails() {

        systemOperationLogger.started(
                SystemOperation.EMAIL_RETRY_JOB,
                SystemOperationType.SCHEDULED,
                SystemDomain.EMAIL
        );

        try {
            emailService.retryFailedEmails();

            systemOperationLogger.completed(
                    SystemOperation.EMAIL_RETRY_JOB,
                    SystemOperationType.SCHEDULED,
                    SystemDomain.EMAIL
            );

        } catch (TechnicalException ex) {
            exceptionLogger.log(ex);

            systemOperationLogger.failed(
                    SystemOperation.EMAIL_RETRY_JOB,
                    SystemOperationType.SCHEDULED,
                    SystemDomain.EMAIL,
                    ex
            );
        }


    }
}