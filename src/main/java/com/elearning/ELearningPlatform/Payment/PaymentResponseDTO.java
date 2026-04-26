package com.elearning.ELearningPlatform.Payment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private String transactionId;
    private String studentName;
    private String paymentType;
    private String status;
    private double amount;
}
