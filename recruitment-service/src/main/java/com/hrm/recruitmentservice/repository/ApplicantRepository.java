package com.hrm.recruitmentservice.repository;

import com.hrm.recruitmentservice.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
}
