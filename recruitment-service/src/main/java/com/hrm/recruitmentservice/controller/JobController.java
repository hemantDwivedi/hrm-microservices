package com.hrm.recruitmentservice.controller;

import com.hrm.recruitmentservice.entity.JobPost;
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
    public ResponseEntity<String> createJob(@Valid @RequestBody JobPost jobPost){
        return new ResponseEntity<>(jobPostService.createJobPost(jobPost), HttpStatus.CREATED);
    }

    // returns a job post by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobPost> getJobPostById(@PathVariable Integer id){
        return ResponseEntity.ok(jobPostService.getJobPostById(id));
    }

    // returns a list of job post
    @GetMapping
    public ResponseEntity<List<JobPost>> getJobPosts(){
        return ResponseEntity.ok(jobPostService.getJobPosts());
    }

    // update job post and returns a confirmation message
    @PutMapping("/{id}")
    public ResponseEntity<String> createJob(@Valid @RequestBody JobPost jobPost, @PathVariable Integer id){
        return ResponseEntity.ok(jobPostService.updateJobPost(jobPost, id));
    }

    // delete a job post and returns a confirmation message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobPostById(@PathVariable Integer id){
        return ResponseEntity.ok(jobPostService.deleteJobPost(id));
    }
}
