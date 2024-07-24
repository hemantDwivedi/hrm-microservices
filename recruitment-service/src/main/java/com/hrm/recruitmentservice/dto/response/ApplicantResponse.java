package com.hrm.recruitmentservice.dto.response;

import java.io.Serializable;

public record ApplicantResponse(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String resumeUrl,
        String location,
        String status
) implements Serializable {
}
