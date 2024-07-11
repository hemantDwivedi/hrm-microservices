package com.hrm.rs.recruitment;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String salaryRange;

    @Column(nullable = false)
    private String jobDescription;

    @Column(nullable = false)
    private String employmentType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String skills;

    private String status;
}