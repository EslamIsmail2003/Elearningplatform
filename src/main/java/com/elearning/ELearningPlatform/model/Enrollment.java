package com.elearning.ELearningPlatform.model;


import com.elearning.ELearningPlatform.enums.EnrollmentStatus;
import com.elearning.ELearningPlatform.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enrollment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Enrollment extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;
}
