package com.example.test.controller.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleBadRequest(IllegalArgumentException  ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                message("Bad Request").
                code("BAD_REQUEST").
                type(TypeErrorEnum.FATAL).
                description("The request is incorrect because the selected parameters are wrong or a functional error has occurred.").
                build();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(ContentNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                message("Not Found").
                code("NOT_FOUND").
                type(TypeErrorEnum.FATAL).
                description("The resource was not found.").
                build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> handleNullPointer(NullPointerException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().
                message("Internal Server Error").
                code("INTERNAL_SERVER_ERROR").
                type(TypeErrorEnum.FATAL).
                description("Unexpected error from the server, it has no way to respond to the invocation.").
                build();

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
