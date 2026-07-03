package com.khaledamin.ims.auth.account.exception;

import com.khaledamin.ims.core.exception.business.BusinessError;
import com.khaledamin.ims.core.exception.business.BusinessException;
import com.khaledamin.ims.core.exception.core.BaseException;

public class AuthException extends BusinessException {

    // ---------------------------------------- Constructors ---------------------------------------- //

    protected AuthException(BusinessError error) {
        super(error);
    }

    protected AuthException(BusinessError error, Throwable cause) {
        super(error, cause);
    }
    // --------------------------------------- End Constructors ---------------------------------------- //

    // ---------------------------------------- Static Methods ---------------------------------------- //

    public static AuthException resetPasswordFailed() {
        return new AuthException(AuthError.RESET_PASSWORD_FAILED);
    }

    public static AuthException resetPasswordFailed(BaseException e) {
        return new AuthException(AuthError.RESET_PASSWORD_FAILED, e);
    }

    public static AuthException activationFailed() {
        return new AuthException(AuthError.ACTIVATION_FAILED);
    }

    public static AuthException activationFailed(BaseException e){
        return new AuthException(AuthError.ACTIVATION_FAILED, e);
    }

    // --------------------------------------- End Static Methods ---------------------------------------- //
}
