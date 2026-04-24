package com.elearning.ELearningPlatform.service;


import com.elearning.ELearningPlatform.model.Enrollment;
import com.elearning.ELearningPlatform.repository.EnrollmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepo enrollmentRepo;

    public EnrollmentService(EnrollmentRepo enrollmentRepo) {
        this.enrollmentRepo = enrollmentRepo;
    }
    public List<Enrollment> getAllEnrollments(){
       return enrollmentRepo.findAll();
    }
    public Optional<Enrollment> getEnrollmentById(String id){
        return enrollmentRepo.findById(id);
    }

    public void deleteEnrollmentById(String id){
         enrollmentRepo.deleteById(id);
    }
    public boolean existsById(String id){
      return enrollmentRepo.existsById(id);

    }
    public Enrollment addEnrollment(Enrollment enrollment){
        return enrollmentRepo.save(enrollment);
    }
}
