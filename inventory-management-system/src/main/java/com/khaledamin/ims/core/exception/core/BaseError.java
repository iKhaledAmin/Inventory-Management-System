package com.khaledamin.ims.core.exception.core;

import com.khaledamin.ims.core.constant.SystemDomain;
import org.springframework.http.HttpStatus;

public interface BaseError {
    SystemDomain getDomain();
    ErrorType getType();
    String getCode();
    HttpStatus getStatus();
    String getMessage();
}
