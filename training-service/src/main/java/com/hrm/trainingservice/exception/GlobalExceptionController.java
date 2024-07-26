package com.hrm.trainingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                          WebRequest request){
        return new
                ResponseEntity<>(
                        new ApiError(
                                HttpStatus.BAD_REQUEST,
                                Objects.requireNonNull(exception.getFieldError()).getDefaultMessage(),
                                request.getDescription(false)
                        ),
                        HttpStatus.BAD_REQUEST
        );
    }
}