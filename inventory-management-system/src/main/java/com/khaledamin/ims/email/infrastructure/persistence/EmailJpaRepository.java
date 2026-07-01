package com.khaledamin.ims.email.infrastructure.persistence;

import com.khaledamin.ims.email.domain.model.Email;
import com.khaledamin.ims.email.domain.model.EmailStatus;
import com.khaledamin.ims.core.persistence.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EmailJpaRepository extends BaseRepository<Email, Long> {


//    List<EmailAddress> findAllByStatusInAndRetryCountLessThanAndLastAttemptAtBefore(
//            List<EmailStatus> emailStatuses,
//            int maxAttempts,
//            LocalDateTime threshold
//    );

    @Query("""
    SELECT DISTINCT e FROM Email e
    LEFT JOIN FETCH e.cc
    LEFT JOIN FETCH e.bcc
    WHERE e.status IN :statuses
    AND e.retryCount < :maxAttempts
    AND e.lastAttemptAt < :threshold
    """)
    List<Email> findRetryableEmailsWithRecipients(
            List<EmailStatus> statuses,
            int maxAttempts,
            LocalDateTime threshold
    );
}
