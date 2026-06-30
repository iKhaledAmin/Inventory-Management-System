package com.khaledamin.ims.identity.account.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalError;
import com.khaledamin.ims.core.exception.technical.TechnicalException;


public class AccountTechnicalException extends TechnicalException {
    // -------------------------------------------- Constructors -------------------------------------------- //
    protected AccountTechnicalException(TechnicalError error) {
        super(error);
    }

    // -------------------------------------------- Factory Method -------------------------------------------- //


    public static AccountTechnicalException nullAccount() {
        return new AccountTechnicalException(AccountTechnicalError.ACCOUNT_NULL);
    }

    public static AccountTechnicalException nullProfile() {
        return new AccountTechnicalException(AccountTechnicalError.PROFILE_NULL);
    }

    public static AccountTechnicalException nullRole() {
        return new AccountTechnicalException(AccountTechnicalError.ROLE_NULL);
    }

    public static AccountTechnicalException nullRoleList() {
        return new AccountTechnicalException(AccountTechnicalError.ROLE_List_NULL);
    }

    public static AccountTechnicalException nullAccountCreateCommand(){
        return new AccountTechnicalException(AccountTechnicalError.ACCOUNT_CREATE_COMMAND_NULL);
    }

    public static AccountTechnicalException nullAccountUpdateCommand() {
        return new AccountTechnicalException(AccountTechnicalError.ACCOUNT_UPDATE_COMMAND_NULL);
    }

    public static AccountTechnicalException nullProfileCreateCommand(){
        return new AccountTechnicalException(AccountTechnicalError.PROFILE_CREATE_COMMAND_NULL);
    }

    public static AccountTechnicalException nullProfileUpdateCommand() {
        return new AccountTechnicalException(AccountTechnicalError.PROFILE_UPDATE_COMMAND_NULL);
    }


    public static AccountTechnicalException profileAlreadyAttached() {
        return new AccountTechnicalException(AccountTechnicalError.PROFILE_ALREADY_ATTACHED);
    }

    public static AccountTechnicalException nullActorIdentity() {
        return new AccountTechnicalException(AccountTechnicalError.ACTOR_IDENTITY_NULL);
    }


    public static AccountTechnicalException nullImage() {
        return new AccountTechnicalException(AccountTechnicalError.IMAGE_NULL);
    }

    public static AccountTechnicalException imageUploadFailed() {
        return new AccountTechnicalException(AccountTechnicalError.IMAGE_UPLOAD_FAILED);
    }
}
