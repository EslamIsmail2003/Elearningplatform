package com.elearning.ELearningPlatform.Instructor;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    private final InstructorRepo instructorRepo;

    public InstructorService(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    public List<InstructorResponseDTO> getAllInstructors() {
        List<Instructor> instructors = instructorRepo.findAll();
        List<InstructorResponseDTO> response = new ArrayList<>();
        for (Instructor instructor : instructors) {
            InstructorResponseDTO dto = mapToDTO(instructor);
            response.add(dto);
        }
        return response;
    }

    public InstructorResponseDTO addInstructor(InstructorRequestDTO request) {
       Instructor instructor = mapToEntity(request);
       Instructor saved = instructorRepo.save(instructor);
       return mapToDTO(saved);
    }

    public void deleteInstructorById(String id) {
        instructorRepo.deleteById(id);
    }

    public boolean existsById(String id) {
        return instructorRepo.existsById(id);
    }

    public Optional<InstructorResponseDTO> getInstructorById(String id) {
        Optional<Instructor> existing = instructorRepo.findById(id);
        if (existing.isPresent()) {
            Instructor instructor = existing.get();
            return Optional.of(mapToDTO(instructor));
        }
        return Optional.empty();
    }

    public Optional<InstructorResponseDTO> updateInstructor(String id, InstructorRequestDTO updatedValues) {
        Optional<Instructor> updated = instructorRepo.findById(id);
        if (updated.isPresent()) {
            Instructor instructor = updated.get();
            updateInstructorFields(instructor, updatedValues);
            Instructor saved = instructorRepo.save(instructor);
            return Optional.of(mapToDTO(saved));
        }
        return Optional.empty();
    }

    private static InstructorResponseDTO mapToDTO(Instructor instructor) {
        return new InstructorResponseDTO(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getEmail(),
                instructor.getCountry(),
                instructor.getScope());
    }

    private static Instructor mapToEntity(InstructorRequestDTO request){
        Instructor instructor = new Instructor();
        instructor.setFirstName(request.getFirstName());
        instructor.setLastName(request.getLastName());
        instructor.setEmail(request.getEmail());
        instructor.setPhone(request.getPhone());
        instructor.setCountry(request.getCountry());
        return instructor;
    }

    private static void updateInstructorFields(Instructor instructor, InstructorRequestDTO updatedValues) {
        instructor.setCountry(updatedValues.getCountry());
        instructor.setEmail(updatedValues.getEmail());
        instructor.setFirstName(updatedValues.getFirstName());
        instructor.setLastName(updatedValues.getLastName());
        instructor.setScope(updatedValues.getScope());
    }
}
