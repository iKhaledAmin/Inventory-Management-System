package com.khaledamin.ims.verification.domain.model;

import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.verification.exception.VerificationException;
import jakarta.persistence.*;
import lombok.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "verification_tokens")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;

    // -------------------- Token --------------------

    @Column(nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false,updatable = false)
    private TokenType tokenType;

    // -------------------- Target --------------------

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "actorType",
                    column = @Column(
                            name = "target_actor_type",
                            nullable = false,
                            updatable = false
                    )
            ),
            @AttributeOverride(
                    name = "actorCode.value",
                    column = @Column(
                            name = "target_actor_code",
                            nullable = false,
                            updatable = false
                    )
            )
    })
    private ActorIdentity target;

    // -------------------- Lifecycle --------------------

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    // -------------------- Factory --------------------

    public static VerificationToken create(
            TokenType type,
            ActorIdentity target,
            int codeLength,
            int expirationMinutes
    ) {

        String code = generateCode(codeLength);
        LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(expirationMinutes);

        return VerificationToken.builder()
                .code(code)
                .tokenType(type)
                .target(target)
                .createdAt(LocalDateTime.now())
                .expiredAt(expiredAt)
                .build();
    }

    // -------------------- Business Methods --------------------

    public void verify(ActorIdentity target, TokenType expectedType) {

        if (!this.target.isSame(target)){
            throw VerificationException.invalidToken()
                    .withDebugDetails("reason","Target account does not match")
                    .withDebugDetails("ExpectedTarget",this.target.getActorCode().toString())
                    .withDebugDetails("ProvidedTarget",target.getActorCode().toString());
        }

        if(!this.tokenType.same(expectedType)){
            throw VerificationException.invalidToken()
                    .withDebugDetails("reason","Token type does not match")
                    .withDebugDetails("ExpectedTokenType",expectedType.name())
                    .withDebugDetails("ActualTokenType",this.tokenType.name());
        }

        if (isVerified()) {
            throw VerificationException.alreadyVerified()
                    .withDebugDetails("VerifiedAt", verifiedAt);
        }

        if (isExpired()) {
            throw VerificationException.expired()
                    .withDebugDetails("expiredAt",expiredAt);
        }

        this.verifiedAt = LocalDateTime.now();
    }


    private boolean isExpired() {
        return LocalDateTime.now().isAfter(expiredAt);
    }

    private boolean isVerified() {
        return verifiedAt != null;
    }


    // -------------------- Helper --------------------

    private static String generateCode(int length) {
        String digits = "0123456789";
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(digits.charAt(random.nextInt(digits.length())));
        }
        return sb.toString();
    }
}