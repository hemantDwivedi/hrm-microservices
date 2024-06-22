package com.hrm.attendancetracking.model;

import com.hrm.attendancetracking.dto.EmployeeClient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId;
    private String date;
    private String inTime;
    private String outTime;
    private String totalWorkingTime;
    private Long employeeId;
}