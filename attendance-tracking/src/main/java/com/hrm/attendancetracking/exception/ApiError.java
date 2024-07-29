package com.hrm.attendancetracking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private HttpStatus status;
    private LocalDate date;
    private LocalTime time;
    private String message;
    private String details;
}