package com.khaledamin.ims.auth.account.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaledamin.ims.identity.account.domain.value.EmailAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "AccountActivationRequest",
        description = "Activate account request"
)
public class AccountActivationRequest {

    @Schema(
            example = "123456",
            description = "Account activation code",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Activation code is mandatory")
    @JsonProperty("activation_code")
    private String activationCode;

    @Schema(
            example = "khaled-amin@example.com",
            description = "Account email address",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = EmailAddress.NULL_ERROR_MESSAGE)
    @Email(message = "Invalid email address")
    @JsonProperty("email_address")
    private String emailAddress;
}