package com.khaledamin.ims.identity.account.api.dto;

import com.khaledamin.ims.identity.account.domain.model.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Schema(
        name = "AccountResponse",
        description = "Account details"
)
public class AccountResponse {

    @Schema(
            example = "ACC-01JY8A7R4W7KX2N8QF5M6P9T3",
            description = "Account unique business identifier"
    )
    @JsonProperty("code")
    private String accountCode;

    @Schema(
            example = "khaled-amin",
            description = "Account username"
    )
    @JsonProperty("username")
    private String username;

    @Schema(
            example = "khaled-amin@example.com",
            description = "Account email address"
    )
    @JsonProperty("email_address")
    private String emailAddress;

    @Schema(
            example = "ACTIVE",
            description = "Current account status"
    )
    @JsonProperty("status")
    private AccountStatus accountStatus;

    @Schema(
            description = "Account profile information"
    )
    @JsonProperty("profile")
    private ProfileResponse profile;
}