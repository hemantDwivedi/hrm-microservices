package com.hrm.rs.recruitment;

public record ApplicantResponse(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String resumeUrl,
        String location,
        String status,
        JobPost job
) {
}
