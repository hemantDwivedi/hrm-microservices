package com.hrm.recruitmentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobPost implements Serializable {
    @Serial
    private static final long serialVersionUID = 214101981905645865L;
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