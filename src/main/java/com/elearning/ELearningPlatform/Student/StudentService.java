package com.elearning.ELearningPlatform.Student;


import com.elearning.ELearningPlatform.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public StudentResponseDTO addStudent(StudentRequestDTO request) {
        Student student = mapToEntity(request);
        Student saved = studentRepo.save(student);
        return mapToDTO(saved);
    }

    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        List<StudentResponseDTO> response = new ArrayList<>();
        for (Student student : students) {
            StudentResponseDTO dto = mapToDTO(student);
            response.add(dto);
        }
        return response;
    }

    public Optional<StudentResponseDTO> getStudentById(String id) {
        Optional<Student> existing = studentRepo.findById(id);
        if (existing.isPresent()) {
            Student student = existing.get();
            return Optional.of(mapToDTO(student));
        } else if (existing.isEmpty()) {
            throw new ResourceNotFoundException("Student not found! " + id);
        }
        return Optional.empty();
    }

    public void deleteStudentById(String id) {
        studentRepo.deleteById(id);
    }

    public boolean existsById(String id) {
        return studentRepo.existsById(id);
    }

    public Optional<StudentResponseDTO> getStudentByEmail(String email) {
        Optional<Student> existing = studentRepo.findByEmail(email);
        if (existing.isPresent()) {
            Student student = existing.get();
            return Optional.of(mapToDTO(student));
        }
        return Optional.empty();
    }

    public Optional<StudentResponseDTO> updateStudent(String id, StudentRequestDTO updatedValues) {
        Optional<Student> existing = studentRepo.findById(id);
        if (existing.isPresent()) {
            Student student = existing.get();
            updateStudentFields(student, updatedValues);
            Student saved = studentRepo.save(student);
            return Optional.of(mapToDTO(saved));
        }
        return Optional.empty();
    }

    private static StudentResponseDTO mapToDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getCountry());
    }

    private static void updateStudentFields(Student student, StudentRequestDTO updatedValues) {
        student.setFirstName(updatedValues.getFirstName());
        student.setLastName(updatedValues.getLastName());
        student.setEmail(updatedValues.getEmail());
        student.setCountry(updatedValues.getCountry());
    }

    private static Student mapToEntity(StudentRequestDTO request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCountry(request.getCountry());
        return student;
    }
}
