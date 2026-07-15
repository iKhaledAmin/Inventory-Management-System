package com.khaledamin.ims.auth.account.api.dto;

import com.khaledamin.ims.identity.account.domain.value.Username;
import com.khaledamin.ims.identity.account.domain.value.RawPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        name = "AccountLoginRequest",
        description = "Account login request"
)
public class AccountLoginRequest {

    @Schema(
            example = "system-admin",
            description = "Account username",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = Username.NULL_ERROR_MESSAGE)
    @NotBlank(message = Username.NULL_ERROR_MESSAGE)
    @JsonProperty("username")
    private String username;

    @Schema(
            example = "Password@123",
            description = "Account password",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = RawPassword.NULL_ERROR_MESSAGE)
    @NotBlank(message = RawPassword.NULL_ERROR_MESSAGE)
    @JsonProperty("password")
    private String password;
}