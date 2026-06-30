package com.khaledamin.ims.organization.exception;

import com.khaledamin.ims.core.exception.business.BusinessError;
import com.khaledamin.ims.core.exception.business.BusinessException;

public class OrganizationBusinessException extends BusinessException {

    // -------------------------------------------- Constructors -------------------------------------------- //

    protected OrganizationBusinessException(BusinessError error) {
        super(error);
    }

    // -------------------------------------------- Static Methods -------------------------------------------- //

    public static OrganizationBusinessException notFound() {
        return new OrganizationBusinessException(OrganizationBusinessError.NOT_FOUND);
    }

}
