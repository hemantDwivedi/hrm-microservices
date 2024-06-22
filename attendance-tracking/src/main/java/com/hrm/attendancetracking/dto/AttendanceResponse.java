package com.hrm.attendancetracking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponse {
    private String date;
    private String inTime;
    private String outTime;
    private String totalWorkingTime;
    private EmployeeClient employee;
}
