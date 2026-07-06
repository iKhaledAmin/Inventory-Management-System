package com.khaledamin.ims.auth.security.core.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@Schema(
        name = "JwtResponse",
        description = "JWT access token details"
)
public class JwtResponse {

    @Schema(
            description = "JWT access token"
    )
    @JsonProperty("access_token")
    private String accessToken;

    @Schema(
            example = "Bearer",
            description = "Authentication scheme"
    )
    @JsonProperty("token_type")
    private String tokenType;

    @Schema(
            example = "3600",
            description = "Token lifetime in seconds"
    )
    @JsonProperty("expires_in")
    private long expiresIn;

    @Schema(
            example = "2026-07-06T17:30:00Z",
            description = "UTC timestamp when the token expires"
    )
    @JsonProperty("expires_at")
    private Instant expiresAt;
}