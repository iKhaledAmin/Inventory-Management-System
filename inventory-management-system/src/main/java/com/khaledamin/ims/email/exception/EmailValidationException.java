package com.khaledamin.ims.email.exception;

import com.khaledamin.ims.core.exception.validation.ValidationError;
import com.khaledamin.ims.core.exception.validation.ValidationException;

public class EmailValidationException extends ValidationException {

    // -------------------------------------------- Constructors -------------------------------------------- //

    protected EmailValidationException(ValidationError error) {
        super(error);
    }

    // -------------------------------------------- Factories -------------------------------------------- //

    public static EmailValidationException invalidEmailAddress() {
        return new EmailValidationException(
                EmailValidationError.EMAIL_ADDRESS_INVALID
        );
    }

    public static EmailValidationException invalidSubject() {
        return new EmailValidationException(
                EmailValidationError.SUBJECT_INVALID
        );
    }

    public static EmailValidationException invalidBody() {
        return new EmailValidationException(
                EmailValidationError.BODY_INVALID
        );
    }

    public static EmailValidationException invalidTemplate() {
        return new EmailValidationException(
                EmailValidationError.TEMPLATE_INVALID
        );
    }




}