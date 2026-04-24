package com.elearning.ELearningPlatform.controller;


import com.elearning.ELearningPlatform.model.Student;
import com.elearning.ELearningPlatform.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
       List<Student> students = studentService.getAllStudents();
       return ResponseEntity.status(HttpStatus.OK).body(students);
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student saved = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id){
        Optional<Student> students = studentService.getStudentById(id);
        if (students.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(students.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable String id){
        if (studentService.existsById(id)){
            studentService.deleteStudentById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> insertStudent(@RequestBody Student updatedValue,@PathVariable String id){
        Optional<Student> updated = studentService.updateStudent(id,updatedValue);
        if (updated.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(updated.get());
        }
       return ResponseEntity.notFound().build();
    }
}