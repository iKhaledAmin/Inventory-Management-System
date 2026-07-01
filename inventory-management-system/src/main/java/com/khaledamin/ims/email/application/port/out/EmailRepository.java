package com.khaledamin.ims.email.application.port.out;

import com.khaledamin.ims.email.domain.model.Email;

import java.time.LocalDateTime;
import java.util.List;


public interface EmailRepository {


    Email save(Email email);

    List<Email> findRetryableEmails(LocalDateTime retryThreshold, int maxAttempts);
}