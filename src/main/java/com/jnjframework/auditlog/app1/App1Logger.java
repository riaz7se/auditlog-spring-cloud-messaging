package com.jnjframework.auditlog.app1;

import com.jnjframework.auditlog.config.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app1")
public class App1Logger {

    @Autowired
    private App1Service app1Service;

    @GetMapping(value = "/log1")
    public Map createRecord() {
        AppDomainA appDomainObjA = AppDomainA.builder().someStr("inputStr").someList(List.of("List 1"))
                .someMap(Map.of("M1", Integer.valueOf(10), "Key2", Double.valueOf(1.4))).build();

        return app1Service.createRecord("arg1Val", appDomainObjA);
    }

    @GetMapping(value = "/log2")
    public String app1NoLog() {
        app1Service.app1NoLog();
        return "app1Details";
    }
}

@Service
class App1Service {

    @AuditLog(appName = "App1", infoType = "INFO-A", operation = "CREATE")
    public Map<String, Object> createRecord(String arg1, AppDomainA appDomainObjA) {
        Map<String, Object> returnMap = new HashMap<>();

        //some logic
        returnMap.put("return1", "return Value 1");
        returnMap.put("return2", AppDomainA.builder().someStr("returnStr").someList(List.of("return some list")).someMap(Map.of("M1", Integer.valueOf(10), "Key2", Double.valueOf(1.4))).build());

        return returnMap;
    }

    public void app1NoLog() {
        System.out.println("No Log");
    }
}