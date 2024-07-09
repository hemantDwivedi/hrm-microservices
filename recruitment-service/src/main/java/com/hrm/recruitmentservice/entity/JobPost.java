package com.hrm.recruitmentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "Job title should not be null")
    private String jobTitle;
    @Column(nullable = false)
    @NotBlank(message = "Address should not be null")
    private String address;
    @Column(nullable = false)
    @NotBlank(message = "Job description should not be null")
    private String jobDescription;
    @Column(nullable = false)
    @NotBlank(message = "Skills should not be null")
    private String skills; // skills: "java,mysql,docker,spring"
}
