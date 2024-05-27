package com.hrm.attendancetracking.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private String message;
    private String details;

    public ApiError() {
        timestamp = new Date();
    }

    public ApiError(HttpStatus status, String message, String details) {
        this();
        this.status = status;
        this.message = message;
        this.details = details;
    }
}
