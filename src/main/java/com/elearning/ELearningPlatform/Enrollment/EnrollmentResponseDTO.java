package com.elearning.ELearningPlatform.Enrollment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDTO {
    private String id;
    private String studentName;
    private String email;
    private String courseName;
    private String status;
    private String paymentStatus;
}
