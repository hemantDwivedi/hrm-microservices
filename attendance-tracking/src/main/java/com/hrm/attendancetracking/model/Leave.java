package com.hrm.attendancetracking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leave_requests")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    @Id
    @GeneratedValue
    private Integer leaveRequestId;
    private String startDate;
    private String endDate;
    private String leaveType;
    private String status;
    private String reason;
    private Long employeeId;
}
