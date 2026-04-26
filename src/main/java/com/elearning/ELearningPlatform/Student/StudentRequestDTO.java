package com.elearning.ELearningPlatform.Student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentRequestDTO {
    @NotBlank(message = "First name is required!")
    private String firstName;
    @NotBlank(message = "Last name is required!")
    private String lastName;
    @Email(message = "Invalid email format!")
    @NotBlank(message = "Email is required!")
    private String email;
    @NotBlank(message = "Phone is required!")
    private String phone;
    @NotBlank(message = "Country is required!")
    private String country;
}
