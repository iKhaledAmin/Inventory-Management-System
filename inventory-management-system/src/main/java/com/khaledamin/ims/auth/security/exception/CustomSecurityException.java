package com.khaledamin.ims.auth.security.exception;


import com.khaledamin.ims.core.exception.core.BaseException;
import lombok.Getter;

@Getter
public class CustomSecurityException extends BaseException {
    private final SecurityError error;

    // ----------------------------------- Constructors ----------------------------------- //

    protected CustomSecurityException(SecurityError error) {
        super(error.getMessage());
        this.error = error;
    }
    protected CustomSecurityException(SecurityError error, Throwable cause){
        super(error.getMessage(),cause);
        this.error = error;
    }
    protected CustomSecurityException(SecurityError error, String message) {
        super(message);
        this.error = error;
    }
    protected CustomSecurityException(SecurityError error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }
    // ---------------------------------- End Constructors ---------------------------------- //

    // ----------------------------------- Factories ----------------------------------- //

    // ================= CREDENTIALS =================

    public static CustomSecurityException invalidCredentials(){
        return new CustomSecurityException(SecurityError.INVALID_CREDENTIALS);
    }

    public static CustomSecurityException authenticationFailed(){
        return new CustomSecurityException(SecurityError.AUTHENTICATION_FAILED);
    }



    // ================= PRINCIPAL =================

    public static CustomSecurityException principalNotFound(String message) {
        return new CustomSecurityException(SecurityError.PRINCIPAL_NOT_FOUND, message);
    }

    public static CustomSecurityException principalLocked(String principalType) {
        return new CustomSecurityException(
                SecurityError.PRINCIPAL_LOCKED,
                principalType + " is locked"
        );
    }

    public static CustomSecurityException principalInactive(String principalType) {
        return new CustomSecurityException(
                SecurityError.PRINCIPAL_INACTIVE,
                principalType + " is not active"
        );
    }


    // ================= TOKEN =================

    public static CustomSecurityException invalidToken() {
        return new CustomSecurityException(SecurityError.TOKEN_INVALID);
    }

    public static CustomSecurityException invalidToken(Throwable cause) {
        return new CustomSecurityException(SecurityError.TOKEN_INVALID, cause);
    }

    public static CustomSecurityException expiredToken() {
        return new CustomSecurityException(SecurityError.TOKEN_EXPIRED);
    }

    public static CustomSecurityException expiredToken(Throwable cause) {
        return new CustomSecurityException(SecurityError.TOKEN_EXPIRED, cause);

    }


//
//    public static SecurityException accessDenied()

}
