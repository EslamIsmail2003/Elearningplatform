package com.elearning.ELearningPlatform.service;


import com.elearning.ELearningPlatform.model.Student;
import com.elearning.ELearningPlatform.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }
    public Optional<Student> getStudentById(String id){
        return studentRepo.findById(id);
    }
    public void deleteStudentById(String id){
        studentRepo.deleteById(id);
    }
    public boolean existsById(String id){
       return studentRepo.existsById(id);
    }
   public Optional<Student> updateStudent(String id, Student updatedValues){
        Optional<Student> existing = studentRepo.findById(id);
        if (existing.isPresent()){
            Student student = existing.get();
            student.setFirstName(updatedValues.getFirstName());
            student.setLastName(updatedValues.getLastName());
            student.setEmail(updatedValues.getEmail());
            student.setPhone(updatedValues.getPhone());
            student.setCountry(updatedValues.getCountry());
            return Optional.of(studentRepo.save(student));
        }
        return Optional.empty();
   }
}
