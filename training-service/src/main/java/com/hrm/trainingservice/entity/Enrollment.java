package com.hrm.trainingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate enrollmentDate;
    private String completionStatus; // completed, learning
    private Long employeeId;
    @ManyToOne
    @JoinColumn(name = "fk_course_id")
    private Course course;
}
