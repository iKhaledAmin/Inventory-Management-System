package com.khaledamin.ims.identity.capability.exception;

import com.khaledamin.ims.core.exception.business.BusinessException;
import com.khaledamin.ims.core.exception.business.BusinessError;

public class CapabilityBusinessException extends BusinessException {

    // ----------------------------------- Constructors ----------------------------------- //

    private CapabilityBusinessException(BusinessError error) {
        super(error);
    }


    // ----------------------------------- Factories ----------------------------------- //

    public static CapabilityBusinessException notFound() {
        return new CapabilityBusinessException(CapabilityBusinessError.NOT_FOUND);
    }

}