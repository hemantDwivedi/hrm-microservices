package com.hrm.attendancetracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
    private String reason;
}