package com.elearning.ELearningPlatform.service;


import com.elearning.ELearningPlatform.model.Instructor;
import com.elearning.ELearningPlatform.repository.InstructorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    private final InstructorRepo instructorRepo;

    public InstructorService(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }
    public List<Instructor> getAllInstructors(){
        return instructorRepo.findAll();
    }
    public Instructor addInstructor(Instructor instructor){
        return instructorRepo.save(instructor);
    }
    public void deleteInstructorById(String id){
        instructorRepo.deleteById(id);
    }
    public boolean existsById(String id){
        return instructorRepo.existsById(id);
    }
    public Optional<Instructor> getInstructorById(String id){
        return instructorRepo.findById(id);
    }
    public Optional<Instructor> updateInstructor(String id, Instructor updatedValues){
        Optional<Instructor> updated = instructorRepo.findById(id);
        if (updated.isPresent()){
            Instructor instructor = updated.get();
            instructor.setCountry(updatedValues.getCountry());
            instructor.setPhone(updatedValues.getPhone());
            instructor.setEmail(updatedValues.getEmail());
            instructor.setFirstName(updatedValues.getFirstName());
            instructor.setLastName(updatedValues.getLastName());
            instructor.setScope(updatedValues.getScope());
            return Optional.of(instructorRepo.save(instructor));
        }
        return Optional.empty();
    }

}
