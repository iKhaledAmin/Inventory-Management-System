package com.khaledamin.ims.auth.account.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "AccountRegistrationResponse",
        description = "Registered account details"
)
public class AccountRegistrationResponse {

    @Schema(
            example = "khaled-amin@example.com",
            description = "Account email address"
    )
    @JsonProperty("email_address")
    private String emailAddress;

    @Schema(
            example = "PENDING_ACTIVATION",
            description = "Current account status"
    )
    @JsonProperty("account_status")
    private String accountStatus;
}