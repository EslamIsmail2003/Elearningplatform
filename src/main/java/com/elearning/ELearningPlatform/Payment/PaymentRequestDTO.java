package com.elearning.ELearningPlatform.Payment;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDTO {
    private String studentId;
    private String transactionId;
    private String paymentType;
    private double amount;
    private String status;
}
