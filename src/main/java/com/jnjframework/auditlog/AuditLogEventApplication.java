package com.jnjframework.auditlog;

import com.jnjframework.auditlog.config.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class AuditLogEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditLogEventApplication.class, args);
	}

}