package com.hrm.rs.recruitment;

import java.util.Set;

public record JobPostResponse(
        Integer id,
        String jobTitle,
        String salaryRange,
        String jobDescription,
        String employmentType,
        String location,
        String skills,
        String status
) {
}
