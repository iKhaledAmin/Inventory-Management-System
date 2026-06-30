package com.khaledamin.ims.core.logging.event;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.logging.definition.SystemOperation;
import com.khaledamin.ims.core.logging.definition.SystemOperationType;

public interface SystemOperationLogger {

    void started(SystemOperation operation, SystemOperationType type, SystemDomain domain);

    void completed(SystemOperation operation, SystemOperationType type, SystemDomain domain);

    void failed(SystemOperation operation, SystemOperationType type, SystemDomain domain, Exception ex);

    void skipped(SystemOperation operation, SystemOperationType type, SystemDomain domain, String reason);

}
