package com.hrm.attendancetracking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    private String startDate;
    private String endDate;
    private String leaveType;
    private String status;
    private String reason;
}