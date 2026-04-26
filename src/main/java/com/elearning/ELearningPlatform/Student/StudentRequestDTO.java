package com.elearning.ELearningPlatform.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;
}
