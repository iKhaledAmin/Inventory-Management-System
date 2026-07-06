package com.khaledamin.ims.auth.client.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaledamin.ims.auth.security.core.jwt.JwtResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class ClientTokenResponse {


    @Schema(
            description = "Authenticated client details"
    )
    @JsonProperty("client_info")
    private ClientInfo client;

    @Schema(
            description = "JWT token details"
    )
    @JsonProperty("token_info")
    private JwtResponse token;





    @Getter
    @SuperBuilder
    public static class ClientInfo {


        @Schema(
                example = "ecommerce-service",
                description = "Registered client identifier"
        )
        @JsonProperty("client_id")
        private String clientId;


        @Schema(
                example = "CLI-01KABC123XYZ456PQR789LMN"
        )
        @JsonProperty("client_code")
        private String clientCode;


        @Schema(
                example = "[\"stock_validate_existence\", \"stock_reserve\", \"stock_release\"]",
                description = "List of client authorities"
        )
        @JsonProperty("authorities")
        private List<String> authorities;
    }
}