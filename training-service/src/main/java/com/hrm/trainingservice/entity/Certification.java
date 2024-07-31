package com.hrm.trainingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private Long employeeId;
    @ManyToOne
    @JoinColumn(name = "fk_course_id")
    private Course course;
}
