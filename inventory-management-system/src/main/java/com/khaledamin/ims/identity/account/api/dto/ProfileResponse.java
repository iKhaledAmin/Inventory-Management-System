package com.khaledamin.ims.identity.account.api.dto;

import com.khaledamin.ims.identity.account.domain.model.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaledamin.ims.media.image.api.dto.ImageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@Schema(
        name = "ProfileResponse",
        description = "Account profile details"
)
public class ProfileResponse {

    @Schema(
            example = "John",
            description = "First name"
    )
    @JsonProperty("first_name")
    private String firstName;

    @Schema(
            example = "Smith",
            description = "Last name"
    )
    @JsonProperty("last_name")
    private String lastName;

    @Schema(
            example = "1998-05-20",
            description = "Birth date"
    )
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @Schema(
            example = "+201001234567",
            description = "Phone number"
    )
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Schema(
            example = "Software Engineer",
            description = "Profession"
    )
    @JsonProperty("profession")
    private String profession;

    @Schema(
            example = "MALE",
            description = "Gender"
    )
    @JsonProperty("gender")
    private Gender gender;

    @Schema(
            name = "image",
            description = "Image details"
    )
    private ImageResponse image;
}