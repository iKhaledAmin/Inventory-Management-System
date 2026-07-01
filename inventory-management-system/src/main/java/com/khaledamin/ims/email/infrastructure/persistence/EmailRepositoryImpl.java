package com.khaledamin.ims.email.infrastructure.persistence;

import com.khaledamin.ims.email.domain.model.Email;
import com.khaledamin.ims.email.domain.model.EmailStatus;
import com.khaledamin.ims.email.application.port.out.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Repository
public class EmailRepositoryImpl implements EmailRepository {

    private final EmailJpaRepository emailJpaRepository;

    @Override
    public Email save(Email email) {
        return emailJpaRepository.save(email);
    }


    @Override
    public List<Email> findRetryableEmails(LocalDateTime threshold, int maxAttempts) {
        return emailJpaRepository
                .findRetryableEmailsWithRecipients(
                        List.of(EmailStatus.FAILED, EmailStatus.RETRYING),
                        maxAttempts,
                        threshold
                );
    }
}
