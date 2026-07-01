package com.khaledamin.ims.verification.domain.repository;

import com.khaledamin.ims.verification.domain.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository {
    VerificationToken save(VerificationToken token);

    Optional<VerificationToken> findOptionalByCode(String code);
}
