package com.hrm.recruitmentservice.service;

import com.hrm.recruitmentservice.dto.request.ApplicantRequest;
import com.hrm.recruitmentservice.dto.response.ApplicantResponse;
import com.hrm.recruitmentservice.exception.ResourceNotFoundException;
import com.hrm.recruitmentservice.helper.ApplicantMapper;
import com.hrm.recruitmentservice.repository.ApplicantRepository;
import com.hrm.recruitmentservice.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final JobPostRepository jobPostRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper mapper;

    public Integer createApplicant(Integer jobId, ApplicantRequest applicantRequest) {
        var jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                format("Job ID::%s not found", jobId)
                        )
                );
        var applicant = mapper.toApplicant(applicantRequest);
        applicant.setJobPost(jobPost);
        var save = applicantRepository.save(applicant);
        return save.getId();
    }

    @Cacheable(value = "applicant", key = "#id")
    public ApplicantResponse findApplicantById(Integer id) {
        var applicant = applicantRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                format("Applicant ID::%s not found", id)
                        )
                );
        return mapper.toApplicantResponse(applicant);
    }

    @Cacheable(value = "applicant")
    public List<ApplicantResponse> findAllApplicants() {
        return applicantRepository
                .findAll()
                .stream()
                .map(mapper::toApplicantResponse)
                .collect(Collectors.toList());
    }

    @Caching(evict = {@CacheEvict(value = "applicant", allEntries = true), @CacheEvict(value = "applicant", key = "#id")})
    public void deleteApplicant(Integer id) {
        applicantRepository.deleteById(id);
    }
}
