package com.elearning.ELearningPlatform.Enrollment;


import com.elearning.ELearningPlatform.Course.Course;
import com.elearning.ELearningPlatform.Payment.PaymentStatus;
import com.elearning.ELearningPlatform.Student.Student;
import com.elearning.ELearningPlatform.utils.BaseEntity;
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

public class Enrollment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String enrollmentStatus;
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;
}
