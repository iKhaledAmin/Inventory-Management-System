package com.khaledamin.ims.identity.account.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaledamin.ims.identity.account.domain.value.*;
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
        name = "AccountCreateRequest",
        description = "Create account request"
)
public class AccountCreateRequest {

    @Schema(
            example = "khaled-amin",
            description = "Unique username",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = Username.NULL_ERROR_MESSAGE)
    @NotBlank(message = Username.NULL_ERROR_MESSAGE)
    @Pattern(regexp = Username.PATTERN, message = Username.PATTERN_ERROR_MESSAGE)
    @Size(max = Username.MAX_LENGTH, message = Username.MAX_LENGTH_ERROR_MESSAGE)
    private String username;

    @Schema(
            example = "Password@123",
            description = "Account password",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = RawPassword.NULL_ERROR_MESSAGE)
    @NotBlank(message = RawPassword.NULL_ERROR_MESSAGE)
    @Size(min = RawPassword.MIN_LENGTH, max = RawPassword.MAX_LENGTH, message = RawPassword.SIZE_ERROR_MESSAGE)
    @Pattern(regexp = RawPassword.PATTERN, message = RawPassword.PATTERN_ERROR_MESSAGE)
    @JsonProperty("password")
    private String password;

    @Schema(
            example = "khaled-amin@example.com",
            description = "Account email address",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = EmailAddress.NULL_ERROR_MESSAGE)
    @NotBlank(message = EmailAddress.NULL_ERROR_MESSAGE)
    @Size(max = EmailAddress.MAX_LENGTH, message = EmailAddress.MAX_LENGTH_ERROR_MESSAGE)
    @Email(message = EmailAddress.PATTERN_ERROR_MESSAGE)
    @JsonProperty("email_address")
    private String emailAddress;

    @Schema(
            example = "Khaled",
            description = "Account owner's first name",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = FirstName.NULL_ERROR_MESSAGE)
    @NotBlank(message = FirstName.NULL_ERROR_MESSAGE)
    @Pattern(regexp = FirstName.PATTERN, message = FirstName.PATTERN_ERROR_MESSAGE)
    @Size(max = FirstName.MAX_LENGTH, message = FirstName.MAX_LENGTH_ERROR_MESSAGE)
    @JsonProperty("first_name")
    private String firstName;

    @Schema(
            example = "Amin",
            description = "Account owner's last name",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = LastName.NULL_ERROR_MESSAGE)
    @NotBlank(message = LastName.NULL_ERROR_MESSAGE)
    @Pattern(regexp = LastName.PATTERN, message = LastName.PATTERN_ERROR_MESSAGE)
    @Size(max = LastName.MAX_LENGTH, message = LastName.MAX_LENGTH_ERROR_MESSAGE)
    @JsonProperty("last_name")
    private String lastName;
}