package com.khaledamin.ims.identity.account.domain.command;

import com.khaledamin.ims.identity.account.api.dto.ProfileUpdateRequest;
import com.khaledamin.ims.identity.account.domain.model.Gender;
import com.khaledamin.ims.identity.account.domain.value.*;

import java.time.LocalDate;
import java.util.Optional;

public record ProfileUpdateCommand(
        Optional<EmailAddress> email,
        Optional<FirstName> firstName,
        Optional<LastName> lastName,
        Optional<Gender> gender,
        Optional<BirthDate> birthDate,
        Optional<PhoneNumber> phoneNumber,
        Optional<Profession> profession
) {

    public static ProfileUpdateCommand of(
            String email,
            String firstName,
            String lastName,
            Gender gender,
            LocalDate birthDate,
            String phoneNumber,
            String profession
    ) {
        return new ProfileUpdateCommand(
                Optional.ofNullable(email).map(EmailAddress::of),
                Optional.ofNullable(firstName).map(FirstName::of),
                Optional.ofNullable(lastName).map(LastName::of),
                Optional.ofNullable(gender),
                Optional.ofNullable(birthDate).map(BirthDate::of),
                Optional.ofNullable(phoneNumber).map(PhoneNumber::of),
                Optional.ofNullable(profession).map(Profession::of)
        );
    }

    public static ProfileUpdateCommand of(ProfileUpdateRequest request) {
        return of(
                request.getEmailAddress(),
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getBirthDate(),
                request.getPhoneNumber(),
                request.getProfession()
        );

    }
}