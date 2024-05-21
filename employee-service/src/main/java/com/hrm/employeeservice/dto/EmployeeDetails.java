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
public class EmployeeDetails {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @JsonProperty("dob")
    private String dateOfBirth;
    private String gender;
    private String city;
    private String state;
    private String pinCode;
    private String startDate;
    private String endDate;
    private String position;
    private String salary;
    private Long departmentId;
}
