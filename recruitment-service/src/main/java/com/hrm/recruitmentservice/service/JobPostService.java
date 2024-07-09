package com.hrm.recruitmentservice.service;

import com.hrm.recruitmentservice.entity.JobPost;
import com.hrm.recruitmentservice.exception.ResourceNotFoundException;
import com.hrm.recruitmentservice.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    // create job post
    public String createJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
        return "Job Post Created";
    }

    // fetch job post by job post ID
    public JobPost getJobPostById(Integer id){
        return jobPostRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Job post not found ID: " + id)
        );
    }

    // fetch all job posts
    public List<JobPost> getJobPosts(){
        return jobPostRepository.findAll();
    }

    // update job post by job post ID
    public String updateJobPost(JobPost jobPost, Integer id) {
        JobPost foundJobPost = jobPostRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Job post not found ID: " + id)
        );

        foundJobPost.setJobTitle(jobPost.getJobTitle());
        foundJobPost.setAddress(jobPost.getAddress());
        foundJobPost.setJobDescription(jobPost.getJobDescription());
        foundJobPost.setSkills(jobPost.getSkills());

        jobPostRepository.save(foundJobPost);
        return "Job post updated";
    }

    // delete a job post by job post ID
    public String deleteJobPost(Integer id) {
        JobPost jobPost = jobPostRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Job post not found ID: " + id)
        );
        jobPostRepository.delete(jobPost);
        return "Job post deleted with ID: " + id;
    }
}
