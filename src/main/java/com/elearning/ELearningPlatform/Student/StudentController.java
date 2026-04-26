package com.elearning.ELearningPlatform.Student;


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
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
       List<StudentResponseDTO> students = studentService.getAllStudents();
       return ResponseEntity.status(HttpStatus.OK).body(students);
    }
    @PostMapping
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentRequestDTO student){
        StudentResponseDTO saved = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable String id){
        Optional<StudentResponseDTO> students = studentService.getStudentById(id);
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
    public ResponseEntity<StudentResponseDTO> updateStudent(@RequestBody StudentRequestDTO updatedValue,@PathVariable String id){
        Optional<StudentResponseDTO> updated = studentService.updateStudent(id,updatedValue);
        if (updated.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(updated.get());
        }
       return ResponseEntity.notFound().build();
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponseDTO> getStudentByEmail(@PathVariable String email){
        Optional<StudentResponseDTO> found = studentService.getStudentByEmail(email);
        if (found.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(found.get());
        }
        return ResponseEntity.noContent().build();
    }
}