package com.elearning.ELearningPlatform.model;


import com.elearning.ELearningPlatform.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private String transactionId;
    private String paymentType;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private double amount;
}
