package com.elearning.ELearningPlatform.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, String> {
    List<Course> findByStatus(CourseStatus status);
    List<Course> findByCategory(String category);
    List<Course> findByInstructorId(String instructorId);
}
