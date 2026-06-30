package com.khaledamin.ims.core.logging.event;

import com.khaledamin.ims.core.exception.business.BusinessException;
import com.khaledamin.ims.core.exception.policy.PolicyException;
import com.khaledamin.ims.core.exception.technical.TechnicalException;
import com.khaledamin.ims.core.exception.validation.ValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ExceptionLogger {


    void log(BusinessException ex);

    void log(PolicyException ex);

    void log(TechnicalException ex);

    void log(ValidationException ex);

    void log(MethodArgumentNotValidException exception);

    void log(Exception ex);
}