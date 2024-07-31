package com.hrm.trainingservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequest {
    private LocalDate enrollmentDate;
    private String completionStatus; // completed, learning
    private Long employeeId;
    private Long courseId;
}
