package com.hrm.pp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payrolls")
public class PayrollRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate payrollDate;
    private Double basicSalary;
    private Double allowances;
    private Double deductions;
    private Double netSalary;
    private Long employeeId;
}
