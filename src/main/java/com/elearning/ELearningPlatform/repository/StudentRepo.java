package com.elearning.ELearningPlatform.repository;


import com.elearning.ELearningPlatform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
}
