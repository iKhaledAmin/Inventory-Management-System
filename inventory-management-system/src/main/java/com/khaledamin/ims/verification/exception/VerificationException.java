package com.khaledamin.ims.verification.exception;


import com.khaledamin.ims.core.exception.business.BusinessError;
import com.khaledamin.ims.core.exception.business.BusinessException;

public class VerificationException extends BusinessException {


    protected VerificationException(BusinessError error) {
        super(error);
    }


    public static VerificationException invalidToken() {
        return new VerificationException(VerificationError.TOKEN_CODE_INVALID);
    }

    public static VerificationException expired() {
        return new VerificationException(VerificationError.TOKEN_EXPIRED);
    }

    public static VerificationException alreadyVerified() {
        return new VerificationException(VerificationError.TOKEN_ALREADY_USED);
    }
}