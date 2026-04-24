package com.elearning.ELearningPlatform.service;


import com.elearning.ELearningPlatform.model.Course;
import com.elearning.ELearningPlatform.model.Instructor;
import com.elearning.ELearningPlatform.repository.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }

    public Optional<Course> getCourseById(String id) {
        return courseRepo.findById(id);
    }

    public boolean existsById(String id) {
        return courseRepo.existsById(id);
    }

    public void deleteCourseById(String id) {
        courseRepo.deleteById(id);
    }
    public Optional<Course> updateCourse(String id, Course updatedValues){
        Optional<Course> updated = courseRepo.findById(id);
        if (updated.isPresent()){
            Course course = updated.get();
            course.setName(updatedValues.getName());
            course.setCategory(updatedValues.getCategory());
            course.setDuration(updatedValues.getDuration());
            course.setPrice(updatedValues.getPrice());
            course.setStatus(updatedValues.getStatus());
            course.setInstructor(updatedValues.getInstructor());
            return Optional.of(courseRepo.save(course));
        }
        return Optional.empty();
    }
}
