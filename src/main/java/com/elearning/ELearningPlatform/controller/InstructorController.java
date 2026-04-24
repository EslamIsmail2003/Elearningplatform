package com.elearning.ELearningPlatform.controller;


import com.elearning.ELearningPlatform.model.Instructor;
import com.elearning.ELearningPlatform.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }
    @PostMapping
    public ResponseEntity<Instructor> addInstructor(@RequestBody Instructor instructor){
        Instructor saved = instructorService.addInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors(){
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.status(HttpStatus.OK).body(instructors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable String id){
        Optional<Instructor> instructor = instructorService.getInstructorById(id);
        if (instructor.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(instructor.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructorById(@PathVariable String id){
        if (instructorService.existsById(id)){
            instructorService.deleteInstructorById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@RequestBody Instructor updatedValue, @PathVariable String id){
        Optional<Instructor> updated=  instructorService.updateInstructor(id, updatedValue);
        if (updated.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
