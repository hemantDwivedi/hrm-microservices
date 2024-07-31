package com.hrm.trainingservice.controller;

import com.hrm.trainingservice.entity.Course;
import com.hrm.trainingservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<String> saveCourse(@RequestBody Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.saveCourse(course));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
    }

    @GetMapping("/course-name/{course-name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable("course-name") String courseName){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseByCourseName(courseName));
    }

    @GetMapping("/instructor/{instructor}")
    public ResponseEntity<Course> getCourseByInstructor(@PathVariable("instructor") String instructor){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseByInstructor(instructor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(course, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.deleteCourse(id));
    }

    @PatchMapping("/{id}")
    public void updateCourseEndDate(@RequestParam("endDate") String endDate, @PathVariable Long id){
        courseService.updateEndDate(id, endDate);
    }
}
