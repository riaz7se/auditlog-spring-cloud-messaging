package com.jnjframework.auditlog.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AuditLogEventData {
    @NotNull String appName;

    @NotNull String infoType;

    @NotNull String operation;

    String auditId;

    String appKey;

    String requestId;

    Map<String, Object> payload = new HashMap<>();

    String updateTimestamp;

    String updateBy;

}
