package com.khaledamin.ims.core.logging.event;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.logging.definition.LogCategory;
import com.khaledamin.ims.core.logging.definition.OperationStatus;
import com.khaledamin.ims.core.logging.definition.SystemOperation;
import com.khaledamin.ims.core.logging.definition.SystemOperationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j(topic = "SystemOperationLogger")
@Component
public class Slf4jSystemOperationLogger implements SystemOperationLogger{


    @Override
    public void started(SystemOperation operation, SystemOperationType type, SystemDomain domain) {
        log.atInfo()
                .addKeyValue("category", LogCategory.SYSTEM_OPERATION)
                .addKeyValue("type", type)
                .addKeyValue("domain", domain)
                .addKeyValue("operation", operation)
                .addKeyValue("status", OperationStatus.STARTED)
                .log(operation.getName() + " operation started");
    }

    @Override
    public void completed(SystemOperation operation, SystemOperationType type, SystemDomain domain) {
        log.atInfo()
                .addKeyValue("category", LogCategory.SYSTEM_OPERATION)
                .addKeyValue("type", type)
                .addKeyValue("domain", domain)
                .addKeyValue("operation", operation)
                .addKeyValue("status", OperationStatus.COMPLETED)
                .log(operation.getName() + " operation completed");
    }

    @Override
    public void failed(SystemOperation operation, SystemOperationType type, SystemDomain domain, Exception ex) {
        log.atError()
                .addKeyValue("category", LogCategory.SYSTEM_OPERATION)
                .addKeyValue("type", type)
                .addKeyValue("domain", domain)
                .addKeyValue("operation", operation)
                .addKeyValue("status", OperationStatus.FAILED)
                .setCause(ex)
                .log(operation.getName() + " operation failed");
    }

    @Override
    public void skipped(SystemOperation operation, SystemOperationType type, SystemDomain domain, String reason) {
        log.atWarn()
                .addKeyValue("category", LogCategory.SYSTEM_OPERATION)
                .addKeyValue("type", type)
                .addKeyValue("domain", domain)
                .addKeyValue("operation", operation)
                .addKeyValue("status", OperationStatus.SKIPPED)
                .addKeyValue("reason", reason)
                .log(operation.getName() + " operation skipped");
    }

}
