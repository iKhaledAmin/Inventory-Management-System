package com.khaledamin.ims.auth.security.core.jwt;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
@Component
public class JwtMapper {

    private final JwtService jwtService;

    public JwtResponse toResponse(String token) {

        Instant expiresAt = jwtService.extractExpiration(token).toInstant();

        long expiresIn = Duration.between(
                Instant.now(),
                expiresAt
        ).getSeconds();

        return JwtResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(expiresIn)
                .expiresAt(expiresAt)
                .build();
    }
}