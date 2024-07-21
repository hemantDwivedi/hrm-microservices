package com.hrm.recruitmentservice.dto.response;

import java.io.Serializable;

public record JobPostResponse(
        Integer id,
        String jobTitle,
        String salaryRange,
        String jobDescription,
        String employmentType,
        String location,
        String skills,
        String status
) implements Serializable {
}
