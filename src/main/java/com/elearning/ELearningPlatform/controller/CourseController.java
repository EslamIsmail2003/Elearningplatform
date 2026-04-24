package com.elearning.ELearningPlatform.controller;


import com.elearning.ELearningPlatform.model.Course;
import com.elearning.ELearningPlatform.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

   public CourseController(CourseService courseService){
       this.courseService = courseService;
   }
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }
   @PostMapping
   public ResponseEntity<Course> addCourse(@RequestBody Course course){
       Course saved = courseService.addCourse(course);
       return ResponseEntity.status(HttpStatus.CREATED).body(saved);
   }
   @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id){
       Optional<Course> course = courseService.getCourseById(id);
       if (course.isPresent()){
           return ResponseEntity.status(HttpStatus.OK).body(course.get());
       }
       return ResponseEntity.notFound().build();
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable String id) {
       if (courseService.existsById(id)) {
           courseService.deleteCourseById(id);
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
   }
   @PutMapping("/{id}")
    public ResponseEntity<Course> insertCourse(@RequestBody Course updatedValues, @PathVariable String id){
       Optional<Course> updated = courseService.updateCourse(id,updatedValues);
       if (updated.isPresent()){
           return ResponseEntity.status(HttpStatus.OK).body(updated.get());
       }
       return ResponseEntity.notFound().build();
   }
}
