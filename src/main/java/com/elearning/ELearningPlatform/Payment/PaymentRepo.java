package com.elearning.ELearningPlatform.Payment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,String> {
    List<Payment> findByStudentId(String studentId);
    List<Payment> findByStatus(PaymentStatus status);
}
