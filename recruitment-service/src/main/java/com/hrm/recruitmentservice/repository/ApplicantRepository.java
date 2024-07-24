package com.hrm.recruitmentservice.repository;

import com.hrm.recruitmentservice.entity.Applicant;
import com.hrm.recruitmentservice.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    List<Applicant> findByJobPost(JobPost jobPost);
}
