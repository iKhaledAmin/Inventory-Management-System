package com.khaledamin.ims.email.exception;

import com.khaledamin.ims.core.exception.business.BusinessError;
import com.khaledamin.ims.core.exception.business.BusinessException;

public class EmailBusinessException extends BusinessException {
    // -------------------------------------------- Constructors -------------------------------------------- //

    protected EmailBusinessException(BusinessError error) {
        super(error);
    }

    // -------------------------------------------- Factories -------------------------------------------- //


    public static EmailBusinessException updateNotAllowed() {
        return new EmailBusinessException(EmailBusinessError.UPDATE_NOT_ALLOWED);
    }

    public static EmailBusinessException invalidTransition() {
        return new EmailBusinessException(
                EmailBusinessError.TRANSITION_INVALID
        );
    }

}
