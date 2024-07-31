package com.hrm.trainingservice.service;

import com.hrm.trainingservice.client.ApiClient;
import com.hrm.trainingservice.dto.request.CertificationRequest;
import com.hrm.trainingservice.entity.Certification;
import com.hrm.trainingservice.entity.Course;
import com.hrm.trainingservice.exception.ResourceNotFoundException;
import com.hrm.trainingservice.repository.CertificationRepository;
import com.hrm.trainingservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CertificationService {
    private final CertificationRepository certificationRepository;
    private final CourseRepository courseRepository;
    private final ApiClient apiClient;

    public String saveCertification(CertificationRequest certificationRequest){
        Boolean employee = apiClient.existById(certificationRequest.getEmployeeId());
        if (!employee) throw new ResourceNotFoundException(format("Employee::%s not found", certificationRequest.getEmployeeId()));
        Course course = courseRepository.findById(certificationRequest.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Course::%s not found", certificationRequest.getCourseId())
                )
        );
        Certification certification = Certification
                .builder()
                .issueDate(certificationRequest.getIssueDate())
                .expiryDate(certificationRequest.getExpiryDate())
                .employeeId(certificationRequest.getEmployeeId())
                .course(course)
                .build();

        Certification dbCertification = certificationRepository.save(certification);
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
