package com.khaledamin.ims.identity.client.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(
        name = "ClientSecretResponse",
        description = "Generated client credentials"
)
public class ClientSecretResponse {

    @Schema(
            description = "Client code",
            example = "CLI-1234567890"
    )
    @JsonProperty("client_code")
    private String clientCode;

    @Schema(
            description = "New generated client secret",
            example = "ims_3Yf8KqL2N9xP7Vb"
    )
    @JsonProperty("client_secret")
    private String clientSecret;
}