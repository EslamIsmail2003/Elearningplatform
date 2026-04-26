package com.elearning.ELearningPlatform.Course;


import com.elearning.ELearningPlatform.Instructor.Instructor;
import com.elearning.ELearningPlatform.Instructor.InstructorRepo;
import com.elearning.ELearningPlatform.Instructor.InstructorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepo courseRepo;
    private final InstructorRepo instructorRepo;

    public CourseService(CourseRepo courseRepo, InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
        this.courseRepo = courseRepo;
    }

    public List<CourseResponseDTO> getAllCourses(){
        List<Course> courses = courseRepo.findAll();
        List<CourseResponseDTO> dto = new ArrayList<>();
        for (Course course : courses){
            CourseResponseDTO response = mapToDTO(course);
            dto.add(response);
        }
        return dto;
    }

    public CourseResponseDTO addCourse(CourseRequestDTO request){
        Course course = mapToEntity(request);
        Course saved = courseRepo.save(course);
        return mapToDTO(saved);
    }

    public void deleteCourseById(String id){
        courseRepo.deleteById(id);
    }


    public boolean existsById(String id){
        return courseRepo.existsById(id);
    }


    public Optional<CourseResponseDTO> getCourseById(String id){
        Optional<Course> existing = courseRepo.findById(id);
        if (existing.isPresent()){
            Course course = existing.get();
            return Optional.of(mapToDTO(course));
        }
        return Optional.empty();
    }
    public Optional<CourseResponseDTO> updateCourseById(String id, CourseRequestDTO request){
        Optional<Course> existing = courseRepo.findById(id);
        if (existing.isPresent()){
            Course course = existing.get();
            updateCourseFields(request,course);
            Course saved = courseRepo.save(course);
            return Optional.of(mapToDTO(saved));
        }
        return Optional.empty();
    }
    public List<CourseResponseDTO> getCourseByStatus(String status){
        List<Course> existing = courseRepo.findByStatus(CourseStatus.valueOf(status));
        List<CourseResponseDTO> dto = new ArrayList<>();
        for (Course course : existing){
            CourseResponseDTO response = mapToDTO(course);
            dto.add(response);
        }
        return dto;
    }

    private static CourseResponseDTO mapToDTO(Course course){
        return new CourseResponseDTO(
                course.getId(),
                course.getCourseName(),
                course.getCategory(),
                course.getStatus().name(),
   course.getInstructor().getFirstName() + " " + course.getInstructor().getLastName(),
                course.getInstructor().getId(),
                course.getDuration(),
                course.getPrice());
    }

    public Course mapToEntity(CourseRequestDTO request){
        Instructor instructor = instructorRepo.findById(request.getInstructorId()).orElseThrow(() -> new RuntimeException("Instructor not found! " + request.getInstructorId()));
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setStatus(CourseStatus.valueOf(request.getStatus()));
        course.setCategory(request.getCategory());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setInstructor(instructor);
        return course;
    }


    private  void updateCourseFields(CourseRequestDTO updatedValues, Course course){
        Instructor instructor = instructorRepo.findById(updatedValues.getInstructorId()).orElseThrow(() -> new RuntimeException("Instructor not found! " + updatedValues.getInstructorId()));
        course.setCourseName(updatedValues.getCourseName());
        course.setStatus(CourseStatus.valueOf(updatedValues.getStatus()));
        course.setCategory(updatedValues.getCategory());
        course.setPrice(updatedValues.getPrice());
        course.setDuration(updatedValues.getDuration());
        course.setInstructor(instructor);
    }
}
