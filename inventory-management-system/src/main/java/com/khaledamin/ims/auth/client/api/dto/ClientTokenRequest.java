package com.khaledamin.ims.auth.client.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaledamin.ims.identity.client.domain.value.ClientId;
import com.khaledamin.ims.identity.client.domain.value.ClientRawSecret;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ClientTokenRequest",
        description = "Machine client authentication request"
)
public class ClientTokenRequest {

    @Schema(
            example = "ecommerce-service",
            description = "Registered client identifier",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = ClientId.NULL_ERROR_MESSAGE)
    @NotBlank(message = ClientId.NULL_ERROR_MESSAGE)
    @JsonProperty("client_id")
    private String clientId;


    @Schema(
            example = "XJ8mR3vL91Qa...",
            description = "Client secret",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = ClientRawSecret.NULL_ERROR_MESSAGE)
    @NotBlank(message = ClientRawSecret.NULL_ERROR_MESSAGE)
    @JsonProperty("client_secret")
    private String clientSecret;

}