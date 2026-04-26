package com.elearning.ELearningPlatform.Course;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> addCourse(@RequestBody CourseRequestDTO request) {
        CourseResponseDTO saved = courseService.addCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable String id) {
        Optional<CourseResponseDTO> course = courseService.getCourseById(id);
        if (course.isPresent()) {
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

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CourseResponseDTO>> getCourseByStatus(@PathVariable String status) {
        List<CourseResponseDTO> response = courseService.getCourseByStatus(status);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<CourseResponseDTO>> getCourseByCategory(@PathVariable String category){
        List<CourseResponseDTO> response = courseService.getCourseByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseResponseDTO>> getCourseByInstructorId(@PathVariable String instructorId){
        List<CourseResponseDTO> response = courseService.getCourseByInstructorId(instructorId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@RequestBody CourseRequestDTO updatedValues, @PathVariable String id) {
        Optional<CourseResponseDTO> updated = courseService.updateCourseById(id, updatedValues);
        if (updated.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
