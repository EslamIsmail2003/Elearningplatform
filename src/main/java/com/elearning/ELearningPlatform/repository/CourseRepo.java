package com.elearning.ELearningPlatform.repository;

import com.elearning.ELearningPlatform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, String> {
}
