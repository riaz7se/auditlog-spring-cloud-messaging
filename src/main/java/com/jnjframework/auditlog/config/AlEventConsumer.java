package com.jnjframework.auditlog.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AlEventConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(AlEventConsumer.class);

    @Bean
    public Consumer<AuditLogEventData> auditLogConsumer() {
        return auditLogEventData -> {
            try {
                LOGGER.info("Consumed Event message: {}", new ObjectMapper().writeValueAsString(auditLogEventData));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };
    }
}