package com.khaledamin.ims.identity.account.exception;


import com.khaledamin.ims.core.exception.validation.ValidationError;
import com.khaledamin.ims.core.exception.validation.ValidationException;

public class AccountValidationException extends ValidationException {

    // ----------------------------------- Constructors ----------------------------------- //

    protected AccountValidationException(ValidationError error) {
        super(error);
    }

    // ----------------------------------- Factories ----------------------------------- //

    public static AccountValidationException invalidId() {
        return new AccountValidationException(AccountValidationError.ID_INVALID);
    }

    public static AccountValidationException invalidFirstName() {
        return new AccountValidationException(AccountValidationError.FIRST_NAME_INVALID);
    }

    public static AccountValidationException invalidLastName() {
        return new AccountValidationException(AccountValidationError.LAST_NAME_INVALID);
    }

    public static AccountValidationException invalidUsername() {
        return new AccountValidationException(AccountValidationError.USERNAME_INVALID);
    }

    public static AccountValidationException invalidEmail() {
        return new AccountValidationException(AccountValidationError.EMAIL_INVALID);
    }

    public static AccountValidationException invalidPhoneNumber() {
        return new AccountValidationException(AccountValidationError.PHONE_NUMBER_INVALID);
    }

    public static AccountValidationException invalidPassword() {
        return new AccountValidationException(AccountValidationError.PASSWORD_INVALID);
    }

    public static AccountValidationException invalidBirthDate() {
        return new AccountValidationException(AccountValidationError.BIRTH_DATE_INVALID);
    }

    public static AccountValidationException invalidProfession() {
        return new AccountValidationException(AccountValidationError.PROFESSION_INVALID);
    }


    public static AccountValidationException invalidSortField() {
        return new AccountValidationException(AccountValidationError.INVALID_SORT_FIELD);
    }
}