package com.hrm.attendancetracking.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRequest {
    private LocalDate date;
    private LocalTime clockIn;
    private LocalTime clockOut;
}