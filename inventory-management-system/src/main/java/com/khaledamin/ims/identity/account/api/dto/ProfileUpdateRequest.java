package com.khaledamin.ims.identity.account.api.dto;

import com.khaledamin.ims.identity.account.domain.model.Gender;
import com.khaledamin.ims.identity.account.domain.value.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "ProfileUpdateRequest",
        description = "Update profile request"
)
public class ProfileUpdateRequest {

    @Schema(
            example = "john.smith@example.com",
            description = "Optional new email address"
    )
    @Size(max = EmailAddress.MAX_LENGTH, message = EmailAddress.MAX_LENGTH_ERROR_MESSAGE)
    @Email(message = EmailAddress.PATTERN_ERROR_MESSAGE)
    private String emailAddress;



    @Schema(
            example = "John",
            description = "Optional first name"
    )
    @Pattern(regexp = FirstName.PATTERN, message = FirstName.PATTERN_ERROR_MESSAGE)
    @Size(max = FirstName.MAX_LENGTH, message = FirstName.MAX_LENGTH_ERROR_MESSAGE)
    private String firstName;

    @Schema(
            example = "Smith",
            description = "Optional last name"
    )
    @Pattern(regexp = LastName.PATTERN, message = LastName.PATTERN_ERROR_MESSAGE)
    @Size(max = LastName.MAX_LENGTH, message = LastName.MAX_LENGTH_ERROR_MESSAGE)
    private String lastName;

    @Schema(
            example = "MALE",
            allowableValues = {
                    "MALE",
                    "FEMALE",
            },
            description = "Optional gender"
    )
    private Gender gender;

    @Schema(
            example = "1998-05-20",
            description = "Optional birth date"
    )
    private LocalDate birthDate;

    @Schema(
            example = "+201001234567",
            description = "Optional phone number"
    )
    @Pattern(regexp = PhoneNumber.PATTERN, message = PhoneNumber.PATTERN_ERROR_MESSAGE)
    private String phoneNumber;

    @Schema(
            example = "Software Engineer",
            description = "Optional profession"
    )
    @Size(max = Profession.MAX_LENGTH, message = Profession.MAX_LENGTH_ERROR_MESSAGE)
    private String profession;

    @Schema(
            description = "Optional profile image",
            type = "string",
            format = "binary"
    )
    @JsonProperty("image")
    private MultipartFile image;
}