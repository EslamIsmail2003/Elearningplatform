package com.elearning.ELearningPlatform.service;


import com.elearning.ELearningPlatform.model.Payment;
import com.elearning.ELearningPlatform.repository.PaymentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepo paymentRepo;

    public PaymentService(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    public Payment addPayment(Payment payment) {
        return paymentRepo.save(payment);
    }
    public  void deletePaymentById(String id){
        paymentRepo.deleteById(id);
    }
    public Optional<Payment> getPaymentById(String id){
        return paymentRepo.findById(id);
    }
    public boolean existsById(String id){
        return paymentRepo.existsById(id);
    }
}

