package com.hrm.recruitmentservice.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record JobPostRequest(
        Integer id,
        @NotBlank(message = "Job title should not be blank")
        String jobTitle,
        @NotBlank(message = "Salary range should not be blank")
        String salaryRange,
        @NotBlank(message = "Job description should not be blank")
        String jobDescription,
        @NotBlank(message = "Employment Type should not be blank")
        String employmentType,
        String location,
        String skills,
        @NotBlank(message = "Status should not be blank")
        String status
) implements Serializable {
}