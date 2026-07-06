package com.khaledamin.ims.identity.client.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(
        name = "ClientResponse",
        description = "Machine client details"
)
public class ClientResponse {

    @Schema(
            description = "Internal client code",
            example = "CLI-01K5Q6R7S8T9U0V1W2X3Y4Z5"
    )
    @JsonProperty("client_code")
    private String clientCode;


    @Schema(
            description = """
                    Public client identifier used during authentication.
                    """,
            example = "ecommerce-production"
    )
    @JsonProperty("client_id")
    private String clientId;


    @Schema(
            description = "Human readable client name",
            example = "E-Commerce Production Integration"
    )
    private String name;


    @Schema(
            description = "Optional client description",
            example = "Production integration between Inventory and E-Commerce systems"
    )
    private String description;


    @Schema(
            description = "Current client status",
            example = "ACTIVE"
    )
    private String status;
}