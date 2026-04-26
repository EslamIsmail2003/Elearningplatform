package com.elearning.ELearningPlatform.Student;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;

}
