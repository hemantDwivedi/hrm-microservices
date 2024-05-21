package com.hrm.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                    WebRequest request) {
        return new
                ResponseEntity<>(
                new ApiError(
                        HttpStatus.NOT_FOUND,
                        exception.getLocalizedMessage(),
                        request.getDescription(false)
                ),
                HttpStatus.NOT_FOUND
        );
    }
}