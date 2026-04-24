package com.elearning.ELearningPlatform.repository;

import com.elearning.ELearningPlatform.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment,String> {

}
