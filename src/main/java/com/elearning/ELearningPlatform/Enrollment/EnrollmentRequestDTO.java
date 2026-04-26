package com.elearning.ELearningPlatform.Enrollment;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnrollmentRequestDTO {
    private String studentId;
    private String courseId;
    private String status;
    private String transactionId;
    private String paymentStatus;
}
