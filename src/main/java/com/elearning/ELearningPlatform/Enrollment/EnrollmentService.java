package com.elearning.ELearningPlatform.Enrollment;


import com.elearning.ELearningPlatform.Course.Course;
import com.elearning.ELearningPlatform.Course.CourseRepo;
import com.elearning.ELearningPlatform.Payment.PaymentStatus;
import com.elearning.ELearningPlatform.Student.Student;
import com.elearning.ELearningPlatform.Student.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepo enrollmentRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    public EnrollmentService(CourseRepo courseRepo,EnrollmentRepo enrollmentRepo, StudentRepo studentRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }
    public List<EnrollmentResponseDTO> getAllEnrollments(){
        List<Enrollment> enrollments = enrollmentRepo.findAll();
        List<EnrollmentResponseDTO> enrollmentResponseDTO = new ArrayList<>();
        for (Enrollment enrollment : enrollments){
            EnrollmentResponseDTO dto = mapToDTO(enrollment);
            enrollmentResponseDTO.add(dto);
        }
        return enrollmentResponseDTO;
    }

    public EnrollmentResponseDTO addEnrollment(EnrollmentRequestDTO request){
        Enrollment enrollment = mapToEntity(request);
        Enrollment saved = enrollmentRepo.save(enrollment);
        return mapToDTO(saved);
    }
    public void deleteEnrollmentById(String id){
        enrollmentRepo.deleteById(id);
    }
    public boolean existsById(String id){
        return enrollmentRepo.existsById(id);
    }
    public Optional<EnrollmentResponseDTO> getEnrollmentById(String id){
        Optional<Enrollment> existing = enrollmentRepo.findById(id);
        if (existing.isPresent()){
            Enrollment enrollment = existing.get();
            return Optional.of(mapToDTO(enrollment));
        }
        return Optional.empty();
    }

    public Optional<EnrollmentResponseDTO> updateEnrollment(String id,EnrollmentRequestDTO request){
        Optional<Enrollment> existing = enrollmentRepo.findById(id);
        if (existing.isPresent()){
            Enrollment enrollment = existing.get();
            updateEnrollmentFields(enrollment,request);
            Enrollment saved = enrollmentRepo.save(enrollment);
            return Optional.of(mapToDTO(saved));
        }
        return Optional.empty();
    }

    public Enrollment mapToEntity(EnrollmentRequestDTO request){
        Student student = studentRepo.findById(request.getStudentId()).orElseThrow(()-> new RuntimeException("Student not found! " + request.getStudentId()));
        Course course = courseRepo.findById(request.getCourseId()).orElseThrow(()-> new RuntimeException("Course not found! "+ request.getCourseId()));
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(EnrollmentStatus.valueOf(request.getStatus()));
        enrollment.setTransactionId(request.getTransactionId());
        enrollment.setPaymentStatus(PaymentStatus.valueOf(request.getPaymentStatus()));
        return enrollment;
    }
    public EnrollmentResponseDTO mapToDTO(Enrollment enrollment){
        return new EnrollmentResponseDTO(
                enrollment.getId(),
                enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName(),
                enrollment.getStudent().getEmail(),
                enrollment.getCourse().getCourseName(),
                enrollment.getStatus().name(),
                enrollment.getPaymentStatus().name());
    }
    public void updateEnrollmentFields(Enrollment enrollment, EnrollmentRequestDTO request){
        Student student = studentRepo.findById(request.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found! " + request.getStudentId()));
        Course course = courseRepo.findById(request.getCourseId()).orElseThrow(()-> new RuntimeException("Course not found! " + request.getCourseId()));
        enrollment.setStatus(EnrollmentStatus.valueOf(request.getStatus()));
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setPaymentStatus(PaymentStatus.valueOf(request.getPaymentStatus()));
        enrollment.setTransactionId(request.getTransactionId());
    }
}
