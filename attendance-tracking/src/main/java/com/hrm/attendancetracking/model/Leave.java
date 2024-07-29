package com.hrm.attendancetracking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
    private String reason;
    private boolean accept;
    private boolean active;
    private Long employeeId;
}