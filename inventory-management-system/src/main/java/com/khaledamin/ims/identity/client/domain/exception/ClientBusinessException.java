package com.khaledamin.ims.identity.client.domain.exception;

import com.khaledamin.ims.core.exception.business.BusinessError;
import com.khaledamin.ims.core.exception.business.BusinessException;
import com.khaledamin.ims.core.exception.core.BaseException;

public class ClientBusinessException extends BusinessException {
    // --------------------------------- Constructors --------------------------------- //
    protected ClientBusinessException(BusinessError error) {
        super(error);
    }

    // --------------------------------- End Constructors ----------------------------- //

    // -------------------------------- Business Methods ---------------------------- //

    public static ClientBusinessException capabilityAlreadyAssigned() {
        return new ClientBusinessException(ClientBusinessError.CAPABILITY_ALREADY_ASSIGNED);
    }

    public static ClientBusinessException capabilityNotAssigned() {
        return new ClientBusinessException(ClientBusinessError.CAPABILITY_NOT_ASSIGNED);
    }

    public static ClientBusinessException notFound() {
        return new ClientBusinessException(ClientBusinessError.NOT_FOUND);
    }

    public static ClientBusinessException clientIdAlreadyExists() {
        return new ClientBusinessException(ClientBusinessError.CLIENT_ID_ALREADY_EXISTS);
    }

    // -------------------------------- End Business Methods ------------------------ //
}
