package com.khaledamin.ims.auth.account.api.dto;

import com.khaledamin.ims.identity.account.domain.value.EmailAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
        name = "AccountResetPasswordRequest",
        description = "Request password reset"
)
public class AccountResetPasswordRequest {

    @Schema(
            example = "khaled-amin@example.com",
            description = "Account email address",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = EmailAddress.NULL_ERROR_MESSAGE)
    @NotBlank(message = EmailAddress.NULL_ERROR_MESSAGE)
    @Email(message = "Invalid email address")
    @JsonProperty("email_address")
    private String emailAddress;
}