package com.khaledamin.ims.core.exception.security;

import com.khaledamin.ims.core.exception.core.BaseError;
import com.khaledamin.ims.core.exception.core.ErrorType;

public interface SecurityError extends BaseError {
    default ErrorType getType() {
        return ErrorType.SECURITY;
    }
}
