package com.jnjframework.auditlog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest(classes =  AuditLogEventApplication.class)
@Import({TestChannelBinderConfiguration.class})
class AuditLogEventApplicationTests {

	@Autowired
	private InputDestination input;

	@Autowired
	private OutputDestination output;

	@Test
	void contextLoads() {
		input.send(new GenericMessage<byte[]>("hello".getBytes()));

		String outputStr = new String(output.receive().getPayload(), StandardCharsets.UTF_8);

		System.out.println("================"+outputStr);

		System.out.println(outputStr.equals("HELLO"));
	}

}
