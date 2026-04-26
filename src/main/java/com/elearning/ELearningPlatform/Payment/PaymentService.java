package com.elearning.ELearningPlatform.Payment;


import com.elearning.ELearningPlatform.Student.Student;
import com.elearning.ELearningPlatform.Student.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepo paymentRepo;
    private final StudentRepo studentRepo;

    public PaymentService(PaymentRepo paymentRepo, StudentRepo studentRepo) {
        this.paymentRepo = paymentRepo;
        this.studentRepo = studentRepo;
    }

    public List<PaymentResponseDTO> getAllPayments() {
        List<Payment> payments = paymentRepo.findAll();
        List<PaymentResponseDTO> response = new ArrayList<>();
        for (Payment payment : payments) {
            PaymentResponseDTO dto = mapToDTO(payment);
            response.add(dto);
        }
        return response;
    }

    public PaymentResponseDTO addPayment(PaymentRequestDTO request) {
        Payment payment = mapToEntity(request);
        Payment saved = paymentRepo.save(payment);
        return mapToDTO(saved);
    }

    public void deletePaymentById(String id) {
        paymentRepo.deleteById(id);
    }

    public Optional<PaymentResponseDTO> getPaymentById(String id) {
        Optional<Payment> existing = paymentRepo.findById(id);
        if (existing.isPresent()) {
            Payment payment = existing.get();
            return Optional.of(mapToDTO(payment));
        }
        return Optional.empty();
    }

    public boolean existsById(String id) {
        return paymentRepo.existsById(id);
    }

    public Optional<PaymentResponseDTO> updatePayment(String id, PaymentRequestDTO updatedValues) {
        Optional<Payment> existing = paymentRepo.findById(id);
        if (existing.isPresent()) {
            Payment payment = existing.get();
            updatePaymentFields(payment, updatedValues);
            Payment saved = paymentRepo.save(payment);
            return Optional.of(mapToDTO(saved));
        }
        return Optional.empty();
    }

    public List<PaymentResponseDTO> getPaymentByStudentId(String studentId){
        List<Payment> payments = paymentRepo.findByStudentId(studentId);
        List<PaymentResponseDTO> response = new ArrayList<>();
        for (Payment payment : payments){
            PaymentResponseDTO dto = mapToDTO(payment);
            response.add(dto);
        }
        return response;
    }

    public List<PaymentResponseDTO> getPaymentsByStatus(String status){
        List<Payment> payments = paymentRepo.findByStatus(PaymentStatus.valueOf(status));
        List<PaymentResponseDTO> response = new ArrayList<>();
        for (Payment payment : payments){
            PaymentResponseDTO dto = mapToDTO(payment);
            response.add(dto);
        }
        return response;
    }
    private static PaymentResponseDTO mapToDTO(Payment payment) {
        return new PaymentResponseDTO(
                payment.getTransactionId(),
                payment.getStudent().getFirstName(),
                payment.getPaymentType(),
                payment.getStatus().name(),
                payment.getAmount());
    }

    private  void updatePaymentFields(Payment payment, PaymentRequestDTO updatedValue) {
        Student student = studentRepo.findById(updatedValue.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found: " + updatedValue.getStudentId()));
        payment.setStudent(student);
        payment.setAmount(updatedValue.getAmount());
        payment.setTransactionId(updatedValue.getTransactionId());
        payment.setPaymentType(updatedValue.getPaymentType());
        payment.setStatus(PaymentStatus.valueOf(updatedValue.getStatus()));
    }

    private Payment mapToEntity(PaymentRequestDTO request) {
        Student student = studentRepo.findById(request.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found with id: " + request.getStudentId()));
        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setTransactionId(request.getTransactionId());
        payment.setPaymentType(request.getPaymentType());
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.valueOf(request.getStatus()));
        return payment;
    }
}

