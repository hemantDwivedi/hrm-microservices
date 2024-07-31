package com.hrm.pp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Payslip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate payslipDate;

    @ManyToOne
    @JoinColumn(name = "fk_payroll_record")
    private PayrollRecord payrollRecord;
}
