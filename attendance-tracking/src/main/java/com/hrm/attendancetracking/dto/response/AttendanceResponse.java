package com.hrm.attendancetracking.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponse {
    private Integer id;
    private LocalDate date;
    private LocalTime clockIn;
    private LocalTime clockOut;
    private LocalTime productive;
}
