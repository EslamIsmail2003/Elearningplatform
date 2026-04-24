package com.elearning.ELearningPlatform.controller;

import com.elearning.ELearningPlatform.model.Enrollment;
import com.elearning.ELearningPlatform.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.status(HttpStatus.OK).body(enrollments);
    }

    @PostMapping
    public ResponseEntity<Enrollment> addEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment saved = enrollmentService.addEnrollment(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentsById(@PathVariable String id) {
        Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(id);
        if (enrollment.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(enrollment.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollmentById(@PathVariable String id) {
        if (enrollmentService.existsById(id)){
            enrollmentService.deleteEnrollmentById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
