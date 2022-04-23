package com.jnjframework.auditlog.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Aspect
@Component
public class AuditLogAspect {

    @Autowired
    private AlEventProducer alEventProducer;

    @Around(value = "@annotation(com.jnjframework.auditlog.config.AuditLog)")
    public Object handle(ProceedingJoinPoint joinPoint) throws NoSuchMethodException, JsonProcessingException {

        var methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method1 = joinPoint.getTarget().getClass().getMethod(methodSignature.getMethod().getName(), methodSignature.getMethod().getParameterTypes());
        Method method = methodSignature.getMethod();
        var annotation = method.getAnnotation(AuditLog.class);

        AuditLogEventData finalAudit_ToLog_AsEvent = prepareLogInfo_FromAnnotation(annotation);

        Object[] methodArgs = joinPoint.getArgs();
        String[] methodParams = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        Map<String, Object> argMap = new HashMap<>();

        IntStream.range(0, methodArgs.length).forEach(i -> argMap.put(methodParams[i], methodArgs[i]));

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("preAudit", argMap); //ar1, appDomainA ....ar1, appDomainA

        Object returnObj = null;
        try {
            //service mtd exec
            returnObj = joinPoint.proceed(joinPoint.getArgs());
            //TODO: condition
            payloadMap.put("postAudit", returnObj); //map ...

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            //Todo
        }

        finalAudit_ToLog_AsEvent.getPayload().putAll(payloadMap);
        //put into EH
        alEventProducer.produceEvent(finalAudit_ToLog_AsEvent);
        return returnObj;
    }

    private AuditLogEventData prepareLogInfo_FromAnnotation(AuditLog annotation) {
        AuditLogEventData auditLogEventData = AuditLogEventData.builder().appName(annotation.appName()).infoType(annotation.infoType()).operation(annotation.operation()).payload(new HashMap<String, Object>()).build();
        return auditLogEventData;
    }


}
