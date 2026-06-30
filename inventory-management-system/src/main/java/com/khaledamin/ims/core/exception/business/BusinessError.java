    package com.khaledamin.ims.core.exception.business;


    import com.khaledamin.ims.core.exception.core.BaseError;
    import com.khaledamin.ims.core.exception.core.ErrorType;

    public interface BusinessError extends BaseError {

        default ErrorType getType() {
            return ErrorType.BUSINESS;
        }
    }