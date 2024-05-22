package com.hrm.employeeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "First name should not be null")
    private String firstName;
    private String lastName;
    @Email(message = "Invalid email")
    @NotBlank(message = "Email should not be null")
    private String email;
    @NotBlank(message = "Invalid phone")
    private String phone;
    @JsonProperty("dob")
    @NotBlank(message = "Invalid date of birth")
    private String dateOfBirth;
    @NotBlank(message = "Invalid gender")
    private String gender;
    @NotBlank(message = "Invalid city")
    private String city;
    @NotBlank(message = "Invalid state")
    private String state;
    @NotBlank(message = "Invalid pin code")
    private String pinCode;
    @NotBlank(message = "Invalid start date")
    private String startDate;
    private String endDate;
    @NotBlank(message = "Invalid position")
    private String position;
    @NotBlank(message = "Invalid salary")
    private String salary;
    private Long departmentId;
}
