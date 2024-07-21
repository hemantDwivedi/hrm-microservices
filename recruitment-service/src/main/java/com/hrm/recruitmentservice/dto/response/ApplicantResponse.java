package com.hrm.recruitmentservice.dto.response;

import com.hrm.recruitmentservice.entity.JobPost;

import java.io.Serializable;

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
) implements Serializable {
}
