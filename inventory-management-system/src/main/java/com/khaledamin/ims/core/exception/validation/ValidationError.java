package com.khaledamin.ims.core.exception.validation;

import com.khaledamin.ims.core.exception.core.BaseError;
import com.khaledamin.ims.core.exception.core.ErrorType;
import org.springframework.http.HttpStatus;

public interface ValidationError extends BaseError {

    default HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }

    default ErrorType getType() {
        return ErrorType.VALIDATION;
    }

}
