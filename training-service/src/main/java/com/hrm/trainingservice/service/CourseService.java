package com.hrm.trainingservice.service;

import com.hrm.trainingservice.entity.Course;
import com.hrm.trainingservice.exception.ResourceNotFoundException;
import com.hrm.trainingservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public String saveCourse(Course course){
        Course dbCourse = courseRepository.save(course);
        return String.format("Course::%s",dbCourse.getId());
    }

    public Course getCourseById(Long id){
        return courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Course ID::%s not found", id)
                )
        );
    }

    public Course getCourseByCourseName(String courseName){
        return courseRepository.findByCourseName(courseName).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Course::%s not found", courseName)
                )
        );
    }

    public Course getCourseByInstructor(String instructor){
        return courseRepository.findByInstructor(instructor).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Course not found Instructor::%s", instructor)
                )
        );
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public String updateCourse(Course course, Long id){
        Course dbCourse = courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Course::%s not found", id)
                )
        );
        mergeCourse(dbCourse, course);
        courseRepository.save(dbCourse);
        return String.format("Course::%s Updated!",id);
    }

    public String deleteCourse(Long id){
        courseRepository.deleteById(id);
        return String.format("Course::%s Deleted!",id);
    }

    public void updateEndDate(Long id, String endDate){
        Course dbCourse = courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Course::%s not found", id)
                )
        );
        dbCourse.setEndDate(LocalDate.parse(endDate));
        courseRepository.save(dbCourse);
    }

    private void mergeCourse(Course dbCourse, Course reqCourse){
        dbCourse.setCourseName(reqCourse.getCourseName());
        dbCourse.setCourseDescription(reqCourse.getCourseDescription());
        dbCourse.setInstructor(reqCourse.getInstructor());
        dbCourse.setStartDate(reqCourse.getStartDate());
        dbCourse.setEndDate(reqCourse.getEndDate());
        dbCourse.setDuration(reqCourse.getDuration());
        dbCourse.setMode(reqCourse.getMode());
    }
}
