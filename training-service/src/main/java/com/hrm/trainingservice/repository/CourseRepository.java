package com.hrm.trainingservice.repository;

import com.hrm.trainingservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseName(String courseName);
    Optional<Course> findByInstructor(String instructor);
}
