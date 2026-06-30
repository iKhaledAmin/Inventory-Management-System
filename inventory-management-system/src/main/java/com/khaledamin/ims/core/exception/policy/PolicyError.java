package com.khaledamin.ims.core.exception.policy;

import com.khaledamin.ims.core.exception.core.BaseError;
import com.khaledamin.ims.core.exception.core.ErrorType;
import org.springframework.http.HttpStatus;

public interface PolicyError extends BaseError {

    default HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }

    default ErrorType getType() {
        return ErrorType.POLICY;
    }

}