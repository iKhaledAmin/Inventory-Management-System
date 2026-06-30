package com.khaledamin.ims.core.logging.event;

import com.khaledamin.ims.core.logging.definition.EventType;
import com.khaledamin.ims.core.logging.definition.LogCategory;
import com.khaledamin.ims.core.logging.definition.RequestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j(topic = "RequestTrackingLogger")
@Component
public class Slf4LRequestEventLoggerImpl implements RequestEventLogger {



    @Override
    public void logStart(String method, String path) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.REQUEST)
                .addKeyValue("event", RequestEvent.REQUEST_STARTED)
                .addKeyValue("method", method)
                .addKeyValue("path", path)
                .log("request started");

    }

    @Override
    public void logComplete(String method, String path, int statusCode, long duration) {

        log.atInfo()
                .addKeyValue("category", LogCategory.EVENT)
                .addKeyValue("type", EventType.REQUEST)
                .addKeyValue("event", RequestEvent.REQUEST_COMPLETED)
                .addKeyValue("method", method)
                .addKeyValue("path", path)
                .addKeyValue("statusCode", statusCode)
                .addKeyValue("duration", duration)
                .log("request completed");

    }
}
