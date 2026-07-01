package com.khaledamin.ims.verification.application.service;


import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.verification.application.config.VerificationProperties;
import com.khaledamin.ims.verification.application.dto.VerificationResult;
import com.khaledamin.ims.verification.domain.model.TokenType;
import com.khaledamin.ims.verification.domain.model.VerificationToken;
import com.khaledamin.ims.verification.domain.repository.VerificationTokenRepository;
import com.khaledamin.ims.verification.exception.VerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final VerificationTokenRepository repository;
    private final VerificationProperties properties;
    private final BusinessEventLogger businessEventLogger;

    @Override
    public String generateToken(TokenType type, ActorIdentity target) {

        int codeLength = properties.getCodeLength(type);
        int expiration = properties.getExpirationMinutes(type);

        VerificationToken token = VerificationToken.create(
                type,
                target,
                codeLength,
                expiration
        );

        repository.save(token);

        businessEventLogger.verificationTokenGenerated(
                token.getId(),
                token.getTokenType().name(),
                token.getTarget().getActorType().toString(),
                token.getTarget().getActorCode().toString()
        );

        return token.getCode();
    }

    @Override
    public VerificationResult verifyToken(String code, ActorIdentity target, TokenType type) {

        VerificationToken token = getOptionalByCode(code).orElseThrow(() -> VerificationException.invalidToken()
                .withDebugDetails("reason", "Token not found")
                .withDebugDetails("tokenCode",code)
        );

        try{
            token.verify(target, type);

            repository.save(token);

            businessEventLogger.tokenVerificationSucceeded(
                    token.getId(),
                    token.getTokenType().name(),
                    token.getTarget().getActorType().toString(),
                    token.getTarget().getActorCode().toString()
            );

            return new VerificationResult(
                    token.getTarget(),
                    token.getTokenType()
            );
        }catch (VerificationException ex){

            businessEventLogger.tokenVerificationFailed(
                    token.getId(),
                    token.getTokenType().name(),
                    token.getTarget().getActorType().toString(),
                    token.getTarget().getActorCode().toString(),
                    ex.getMessage()
            );

            throw ex;
        }


    }


    private Optional<VerificationToken> getOptionalByCode(String code){
        return repository.findOptionalByCode(code);
    }

}
