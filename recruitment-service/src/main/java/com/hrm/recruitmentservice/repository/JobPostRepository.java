package com.hrm.recruitmentservice.repository;

import com.hrm.recruitmentservice.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
}
