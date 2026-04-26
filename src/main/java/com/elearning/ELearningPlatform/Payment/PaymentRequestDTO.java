package com.elearning.ELearningPlatform.Payment;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDTO {
    @NotBlank(message = "Student id is required!")
    private String studentId;
    @NotBlank(message = "Transaction id is required!")
    private String transactionId;
    @NotBlank(message = "Payment type is required!")
    private String paymentType;
    @Positive(message = "Amount can't be in negative!")
    @NotNull(message = "Amount is required!")
    private double amount;
    @NotBlank(message = "Status is required!")
    private String status;
}
