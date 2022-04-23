package com.jnjframework.auditlog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class AlEventProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(AlEventProducer.class);

    @Autowired
    private StreamBridge streamBridge;

    //Async
    public void produceEvent(AuditLogEventData auditLogEventLog) {
        streamBridge.send("auditProducer-out-0", auditLogEventLog);
        LOGGER.info("Produced Event message: ");
    }
}
