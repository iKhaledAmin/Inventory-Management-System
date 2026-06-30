package com.khaledamin.ims.identity.account.exception;

import com.khaledamin.ims.core.exception.business.BusinessError;
import com.khaledamin.ims.core.exception.business.BusinessException;

public class AccountBusinessException extends BusinessException {
    // -------------------------------------------- Constructors -------------------------------------------- //
    protected AccountBusinessException(BusinessError error) {
        super(error);
    }

    // -------------------------------------------- Factory Method -------------------------------------------- //

    public static AccountBusinessException notFound() {
        return new AccountBusinessException(AccountBusinessError.NOT_FOUND);
    }

    public static AccountBusinessException duplicateRoles() {
        return new AccountBusinessException(AccountBusinessError.ROLE_DUPLICATE);
    }

    public static AccountBusinessException emptyRoleList() {
        return new AccountBusinessException(AccountBusinessError.ROLE_LIST_EMPTY);
    }

    public static AccountBusinessException missingIdentityRole() {
        return new AccountBusinessException(AccountBusinessError.IDENTITY_ROLE_MISSING);
    }

    public static AccountBusinessException passwordResetNotAllowed() {
        return new AccountBusinessException(AccountBusinessError.PASSWORD_RESET_NOT_ALLOWED);
    }

    public static AccountBusinessException roleAssignNotAllowed() {
        return new AccountBusinessException(AccountBusinessError.ROLE_ASSIGN_NOT_ALLOWED);
    }

    public static AccountBusinessException roleRemovalNotAllowed() {
        return new AccountBusinessException(AccountBusinessError.ROLE_REMOVE_NOT_ALLOWED);
    }
    public static AccountBusinessException usernameAlreadyExists() {
        return new AccountBusinessException(AccountBusinessError.USERNAME_ALREADY_EXISTS);
    }

    public static AccountBusinessException emailAlreadyExists() {
        return new AccountBusinessException(AccountBusinessError.EMAIL_ALREADY_EXISTS);
    }

    public static AccountBusinessException lastAdminRemovalNotAllowed() {
        return new AccountBusinessException(AccountBusinessError.LAST_ADMIN_REMOVAL_NOT_ALLOWED);
    }


}
