package com.hrm.recruitmentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Applicant implements Serializable {
    @Serial
    private static final long serialVersionUID = 214101981905645865L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email")
    private String email;

    @Column(nullable = false)
    @Size(min = 10, max = 10)
    private String phone;

    private String resumeUrl;

    @Column(nullable = false)
    private String location;

    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;
}
