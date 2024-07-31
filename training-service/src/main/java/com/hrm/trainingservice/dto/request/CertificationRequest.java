package com.hrm.trainingservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CertificationRequest {
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private Long employeeId;
    private Long courseId;
}
