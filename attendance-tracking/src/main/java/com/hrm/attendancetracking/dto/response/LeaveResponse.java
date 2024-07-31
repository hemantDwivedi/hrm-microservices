package com.hrm.attendancetracking.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveResponse {
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
    private String reason;
    private boolean accept;
    private boolean active;
}
