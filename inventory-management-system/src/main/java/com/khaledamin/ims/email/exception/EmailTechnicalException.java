package com.khaledamin.ims.email.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalError;
import com.khaledamin.ims.core.exception.technical.TechnicalException;

public class EmailTechnicalException extends TechnicalException {
    // -------------------------------------------- Constructors -------------------------------------------- //

    protected EmailTechnicalException(TechnicalError error) {
        super(error);
    }

    protected EmailTechnicalException(TechnicalError error, Throwable cause) {
        super(error, cause);
    }


    // -------------------------------------------- Factories -------------------------------------------- //

    public static EmailTechnicalException nullUpdateCommand() {
        return new EmailTechnicalException(EmailTechnicalError.UPDATE_COMMAND_NULL);
    }

    public static EmailTechnicalException nullEmail() {
        return new EmailTechnicalException(EmailTechnicalError.EMAIL_NULL);
    }

    public static EmailTechnicalException nullFailureReason() {
        return new EmailTechnicalException(EmailTechnicalError.FAILURE_REASON_NULL);
    }

    public static EmailTechnicalException templateRenderingFailed(Throwable cause) {
        return new EmailTechnicalException(EmailTechnicalError.TEMPLATE_RENDERING_FAILED, cause);
    }

    public static EmailTechnicalException emailSendingFailed(Throwable cause) {
        return new EmailTechnicalException(EmailTechnicalError.EMAIL_SENDING_FAILED, cause);
    }
}
