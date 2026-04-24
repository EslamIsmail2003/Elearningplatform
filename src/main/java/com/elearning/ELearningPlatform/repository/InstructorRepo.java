package com.elearning.ELearningPlatform.repository;

import com.elearning.ELearningPlatform.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, String> {
}
