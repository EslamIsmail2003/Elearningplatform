package com.elearning.ELearningPlatform.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment,String> {
    List<Enrollment> findEnrollmentByStudentId(String studentId);
}
