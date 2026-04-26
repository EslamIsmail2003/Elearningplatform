package com.elearning.ELearningPlatform.Enrollment;

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
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollments() {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.status(HttpStatus.OK).body(enrollments);
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> addEnrollment(@RequestBody EnrollmentRequestDTO request) {
        EnrollmentResponseDTO saved = enrollmentService.addEnrollment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentsById(@PathVariable String id) {
        Optional<EnrollmentResponseDTO> enrollment = enrollmentService.getEnrollmentById(id);
        if (enrollment.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(enrollment.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentByStudentId(@PathVariable String studentId){
        List<EnrollmentResponseDTO> response = enrollmentService.findEnrollmentByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollmentById(@PathVariable String id) {
        if (enrollmentService.existsById(id)){
            enrollmentService.deleteEnrollmentById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> updateEnrollment(@RequestBody EnrollmentRequestDTO updatedValue, @PathVariable String id){
        Optional<EnrollmentResponseDTO> updated = enrollmentService.updateEnrollment(id, updatedValue);
        if (updated.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
