package com.hrm.employeeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @JsonProperty("dob")
    private String dateOfBirth;
    private String gender;
}
