package com.khaledamin.ims.auth.account.api.dto;

import com.khaledamin.ims.identity.account.domain.value.EmailAddress;
import com.khaledamin.ims.identity.account.domain.value.RawPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "AccountConfirmResetPasswordRequest",
        description = "Confirm reset password request"
)
public class AccountConfirmResetPasswordRequest {

    @Schema(
            example = "987654",
            description = "Password reset verification code",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Reset code is mandatory")
    @NotBlank(message = "Reset code is mandatory")
    @JsonProperty("reset_code")
    private String resetCode;

    @Schema(
            example = "khaled-amin@example.com",
            description = "Account email address",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = EmailAddress.NULL_ERROR_MESSAGE)
    @NotBlank(message = EmailAddress.NULL_ERROR_MESSAGE)
    @Email(message = EmailAddress.PATTERN_ERROR_MESSAGE)
    @JsonProperty("email_address")
    private String emailAddress;

    @Schema(
            example = "Password@123",
            description = "New account password",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = RawPassword.NULL_ERROR_MESSAGE)
    @NotBlank(message = RawPassword.NULL_ERROR_MESSAGE)
    @Size(min = RawPassword.MIN_LENGTH, max = RawPassword.MAX_LENGTH, message = RawPassword.SIZE_ERROR_MESSAGE)
    @Pattern(regexp = RawPassword.PATTERN, message = RawPassword.PATTERN_ERROR_MESSAGE)
    @JsonProperty("password")
    private String password;
}