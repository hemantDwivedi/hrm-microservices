package com.hrm.trainingservice.service;

import com.hrm.trainingservice.client.ApiClient;
import com.hrm.trainingservice.entity.Certification;
import com.hrm.trainingservice.entity.Course;
import com.hrm.trainingservice.exception.ResourceNotFoundException;
import com.hrm.trainingservice.repository.CertificationRepository;
import com.hrm.trainingservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificationService {
    private final CertificationRepository certificationRepository;
    private final CourseRepository courseRepository;
    private final ApiClient apiClient;

    public String saveCertification(Certification certification, Long empId, Long courseId){
        Boolean employee = apiClient.existById(empId);
        if (!employee) throw new ResourceNotFoundException("Employee not found");
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Course::%s not found", courseId)
                )
        );
        certification.setEmployeeId(empId);
        certification.setCourse(course);

        Certification dbCertification = certificationRepository.save(certification);
        log.info("Certification issue Date:: {}", dbCertification.getIssueDate());
        log.info("Certification expiry Date {}", dbCertification.getExpiryDate());
        return format("Certification::%s", dbCertification.getId());
    }

    public Certification getCertificationById(Long id){
        return certificationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Certification::%s not found", id)
                )
        );
    }

    public List<Certification> getCertificationByEmployeeId(Long id){
        List<Certification> certifications = certificationRepository.findByEmployeeId(id);
        if (certifications.isEmpty()){
                throw new ResourceNotFoundException(
                        format("Certification::%s not found", id)
                );
        }
        return certifications;
    }

    public List<Certification> getAllCertifications(){
        return certificationRepository.findAll();
    }

    public String deleteCertification(Long id){
        certificationRepository.deleteById(id);
        return format("Certification::%s deleted!", id);
    }
}
