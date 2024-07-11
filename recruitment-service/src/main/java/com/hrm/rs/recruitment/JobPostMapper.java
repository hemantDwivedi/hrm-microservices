package com.hrm.rs.recruitment;

import org.springframework.stereotype.Service;

@Service
public class JobPostMapper {
    public JobPost toJobPost(JobPostRequest jobPostRequest){
        return
                JobPost
                        .builder()
                        .jobTitle(jobPostRequest.jobTitle())
                        .salaryRange(jobPostRequest.salaryRange())
                        .jobDescription(jobPostRequest.jobDescription())
                        .employmentType(jobPostRequest.employmentType())
                        .location(jobPostRequest.location())
                        .skills(jobPostRequest.skills())
                        .status(jobPostRequest.status())
                        .build();
    }

    public JobPostResponse toJobPostResponse(JobPost jobPost) {
        return new JobPostResponse(
                jobPost.getId(),
                jobPost.getJobTitle(),
                jobPost.getSalaryRange(),
                jobPost.getJobDescription(),
                jobPost.getEmploymentType(),
                jobPost.getLocation(),
                jobPost.getSkills(),
                jobPost.getStatus()
        );
    }
}
