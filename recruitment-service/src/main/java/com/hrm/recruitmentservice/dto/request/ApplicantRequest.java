package com.hrm.recruitmentservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record ApplicantRequest(
        Integer id,
        @NotBlank(message = "First name should not be blank")
        String firstName,
        @NotBlank(message = "Last name should not be blank")
        String lastName,
        @NotBlank(message = "Email should not be blank")
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "Phone should not be blank")
        @Size(min = 10, max = 10)
        String phone,
        @NotBlank(message = "Resume URL should not be blank")
        String resumeUrl,
        @NotBlank(message = "Location should not be blank")
        String location,
        @NotBlank(message = "Status should not be Blank")
        String status
) implements Serializable {
}
