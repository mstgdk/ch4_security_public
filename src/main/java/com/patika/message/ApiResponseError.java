package com.patika.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiResponseError {
    private HttpStatus status;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String requestURI ;

    private ApiResponseError(){
        timestamp = LocalDateTime.now();
    }
    public ApiResponseError(HttpStatus status, String message, String requestURI) {
        this();
        this.status=status; // yukardaki 1 parametreli, public const. çağrılıyor
        this.message = message;
        this.requestURI = requestURI;
    }
}
