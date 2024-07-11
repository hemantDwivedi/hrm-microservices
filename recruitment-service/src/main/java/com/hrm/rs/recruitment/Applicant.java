package com.hrm.rs.recruitment;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Applicant {
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
