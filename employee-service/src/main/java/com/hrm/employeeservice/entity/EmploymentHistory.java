package com.hrm.employeeservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentHistory {
    @Id
    @GeneratedValue
    private Integer employmentHistoryId;
    private String startDate;
    private String endDate;
    private String position;
    private String salary;
}
