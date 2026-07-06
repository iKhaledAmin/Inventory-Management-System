package com.khaledamin.ims.identity.client.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaledamin.ims.identity.client.domain.value.ClientId;
import com.khaledamin.ims.identity.client.domain.value.ClientName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ClientCreateRequest",
        description = "Request used to create a machine client"
)
public class ClientCreateRequest {

    @Schema(
            name = "client_id",
            description = """
                    Unique machine identifier used during client authentication.

                    Rules:
                    - Must be unique across the system
                    - Used during client login
                    - Cannot be changed after creation
                    """,
            example = "ecommerce-production",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = ClientId.NULL_ERROR_MESSAGE)
    @NotBlank(message = ClientId.NULL_ERROR_MESSAGE)
    @Pattern(regexp = ClientId.PATTERN, message = ClientId.PATTERN_ERROR_MESSAGE)
    @Size(max = ClientId.MAX_LENGTH, message = ClientId.MAX_LENGTH_ERROR_MESSAGE)
    @JsonProperty("client_id")
    private String clientId;


    @Schema(
            description = """
                    Human readable client name.
                    
                    Used only for administration and identification.
                    """,
            example = "E-Commerce Production Integration",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = ClientName.NULL_ERROR_MESSAGE)
    @NotBlank(message = ClientName.NULL_ERROR_MESSAGE)
    @Size(
            max = ClientName.MAX_LENGTH,
            message = ClientName.MAX_LENGTH_ERROR_MESSAGE
    )
    private String name;


    @Schema(
            description = """
                    Optional client description.
                    """,
            example = "Production integration between Inventory and E-Commerce systems",
            nullable = true
    )
    @JsonProperty("description")
    private String description;
}