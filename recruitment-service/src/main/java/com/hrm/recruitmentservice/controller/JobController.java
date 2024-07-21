package com.hrm.recruitmentservice.controller;

import com.hrm.recruitmentservice.dto.request.JobPostRequest;
import com.hrm.recruitmentservice.dto.response.JobPostResponse;
import com.hrm.recruitmentservice.service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobPostService jobPostService;

    // save job post in database and returns a string message
    @PostMapping
    public ResponseEntity<String> createJob(@Valid @RequestBody JobPostRequest jobPostRequest){
        return new ResponseEntity<>(jobPostService.createJobPost(jobPostRequest), HttpStatus.CREATED);
    }

    // returns a job post by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobPostResponse> getJobPostById(@PathVariable Integer id){
        return ResponseEntity.ok(jobPostService.getJobPostById(id));
    }

    // returns a list of job post
    @GetMapping
    public ResponseEntity<List<JobPostResponse>> getJobPosts(){
        return ResponseEntity.ok(jobPostService.getJobPosts());
    }

    // update job post
    @PutMapping
    public void updateJobPost(@Valid @RequestBody JobPostRequest jobPostRequest){
        jobPostService.updateJobPost(jobPostRequest);
    }

    // delete a job post and returns a confirmation message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobPostById(@PathVariable Integer id){
        return ResponseEntity.ok(jobPostService.deleteJobPost(id));
    }
}
