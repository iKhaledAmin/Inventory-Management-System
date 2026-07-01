package com.khaledamin.ims.verification.infrastructure.persistence;

import com.khaledamin.ims.verification.domain.model.VerificationToken;
import com.khaledamin.ims.verification.domain.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

    private final VerificationTokenJpaRepository verificationTokenJpaRepository;

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenJpaRepository.save(verificationToken);
    }

    @Override
    public Optional<VerificationToken> findOptionalByCode(String code) {
        return verificationTokenJpaRepository.findByCode(code);
    }

}
