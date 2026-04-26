package com.elearning.ELearningPlatform.Enrollment;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnrollmentRequestDTO {
    @NotBlank(message = "Student Id is required! ")
    private String studentId;
    @NotBlank(message = "Course Id is required!")
    private String courseId;
    @NotBlank(message = "Status is required!")
    private String status;
    @NotBlank(message = "Transaction id is required!")
    private String transactionId;
    @NotBlank(message = "Payment status is required!")
    private String paymentStatus;
}
