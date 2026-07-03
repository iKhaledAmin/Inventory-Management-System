package com.khaledamin.ims.auth.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(
        name = "JwtResponse",
        description = "JWT access token details"
)
public class JwtResponse {

    @Schema(
            example = "eyJhbGciOiJI.eyJzdWIiOiJ0ZXN0IiwiaWF0Ijo........",
            description = "JWT access token"
    )
    @JsonProperty("token")
    private String accessToken;

    @Schema(
            example = "Bearer",
            description = "Token type"
    )
    @JsonProperty("type")
    private String type;

    @Schema(
            example = "1782748800",
            description = "Token expiration timestamp in epoch seconds"
    )
    @JsonProperty("expires_at")
    private long expiresAt;
}