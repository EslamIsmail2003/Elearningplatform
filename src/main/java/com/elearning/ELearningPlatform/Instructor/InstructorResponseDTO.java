package com.elearning.ELearningPlatform.Instructor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String scope;
}
