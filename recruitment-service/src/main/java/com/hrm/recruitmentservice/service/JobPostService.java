package com.hrm.recruitmentservice.service;

import com.hrm.recruitmentservice.dto.request.JobPostRequest;
import com.hrm.recruitmentservice.dto.response.JobPostResponse;
import com.hrm.recruitmentservice.entity.JobPost;
import com.hrm.recruitmentservice.exception.ResourceNotFoundException;
import com.hrm.recruitmentservice.helper.JobPostMapper;
import com.hrm.recruitmentservice.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;
    private final JobPostMapper jobPostMapper;

    // create job post
    public String createJobPost(JobPostRequest jobPostRequest) {
        jobPostRepository.save(jobPostMapper.toJobPost(jobPostRequest));
        return "Job Post Created";
    }

    // fetch job post by job post ID
    @Cacheable(value = "job", key = "#id")
    public JobPostResponse getJobPostById(Integer id) {
        var jobPost = jobPostRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("No job post found with provided ID:: %s", id)
                )
        );
        return jobPostMapper.toJobPostResponse(jobPost);
    }

    // fetch all job posts
    @Cacheable(value = "job")
    public List<JobPostResponse> getJobPosts() {
        return jobPostRepository
                .findAll()
                .stream()
                .map(jobPostMapper::toJobPostResponse)
                .collect(Collectors.toList());

    }

    // update job post by job post ID
    public void updateJobPost(JobPostRequest jobPostRequest) {
        var jobPost = jobPostRepository.findById(jobPostRequest.id()).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Cannot update job post, No job post found with provided ID:: %s", jobPostRequest.id())
                )
        );

        mergeJobPost(jobPost, jobPostRequest);
        jobPostRepository.save(jobPost);
    }

    // delete a job post by job post ID
    @Caching(evict = {@CacheEvict(value = "job", allEntries = true), @CacheEvict(value = "job", key = "#id")})
    public String deleteJobPost(Integer id) {
        jobPostRepository.deleteById(id);
        return "Job ID:: " + id + " deleted";
    }

    // This method make sures to not override existing data properties with null/empty values
    private void mergeJobPost(JobPost foundJobPost, JobPostRequest jobPostRequest) {
        if (isNotBlank(jobPostRequest.jobTitle())) foundJobPost.setJobTitle(jobPostRequest.jobTitle());
        if (isNotBlank(jobPostRequest.salaryRange())) foundJobPost.setSalaryRange(jobPostRequest.salaryRange());
        if (isNotBlank(jobPostRequest.jobDescription()))
            foundJobPost.setJobDescription(jobPostRequest.jobDescription());
        if (isNotBlank(jobPostRequest.employmentType()))
            foundJobPost.setEmploymentType(jobPostRequest.employmentType());
        if (isNotBlank(jobPostRequest.location())) foundJobPost.setLocation(jobPostRequest.location());
        if (!jobPostRequest.skills().isEmpty()) foundJobPost.setSkills(jobPostRequest.skills());
        if (isNotBlank(jobPostRequest.status())) foundJobPost.setStatus(jobPostRequest.status());
    }
}
