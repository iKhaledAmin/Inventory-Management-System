package com.khaledamin.ims.verification.infrastructure.persistence;

import com.khaledamin.ims.core.persistence.BaseRepository;
import com.khaledamin.ims.verification.domain.model.VerificationToken;
import java.util.Optional;

public interface VerificationTokenJpaRepository extends BaseRepository<VerificationToken,Long> {
    Optional<VerificationToken> findByCode(String code);
}
