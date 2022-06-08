# Run Application on Local
### Using Kafka
#### in application.properties set below

````properties
spring.cloud.stream.default-binder=kafka
````
### Using Azure Event Hub

```properties
spring.cloud.stream.default-binder=eventhub
```

#### Run AuditLogEventAplication 
### On Local Kafka can be used as Messaging sytem

https://github.com/Azure-Samples/azure-spring-data-cosmos-java-sql-api-getting-started


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlDataException extends RuntimeException {
    public AlDataException() {
        super();
    }
    public AlDataException(String message, Throwable cause) {
        super(message, cause);
    }
    public AlDataException(String message) {
        super(message);
    }
    public AlDataException(Throwable cause) {
        super(cause);
    }
}




import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//@ControllerAdvice
@RestControllerAdvice
public class AlExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AlExceptionHandler.class);

    @ExceptionHandler(value
            = { JsonProcessingException.class, })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleException(
            RuntimeException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        String bodyOfResponse = "Error while processing Request";
        return bodyOfResponse;
    }

    @ExceptionHandler(value
            = { AlDataException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleAlException(
            Throwable ex) {
        String bodyOfResponse = "Error while processing Al Request: "+ex.getMessage();
        return bodyOfResponse;
    }

}
