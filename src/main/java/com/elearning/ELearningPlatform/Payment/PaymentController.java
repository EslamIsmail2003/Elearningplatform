package com.elearning.ELearningPlatform.Payment;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> addPayment(@Valid @RequestBody PaymentRequestDTO payment){
        PaymentResponseDTO saved = paymentService.addPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments(){
        List<PaymentResponseDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.status(HttpStatus.OK).body(payments);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable String id){
        Optional<PaymentResponseDTO> payments = paymentService.getPaymentById(id);
        if (payments.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(payments.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentById(@PathVariable String id){
       if (paymentService.existsById(id)){
           paymentService.deletePaymentById(id);
           return ResponseEntity.noContent().build();
       }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> updatePayment(@Valid @RequestBody PaymentRequestDTO updatedValue, @PathVariable String id){
        Optional<PaymentResponseDTO> updated = paymentService.updatePayment(id, updatedValue);
        if (updated.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentByStudentId(@PathVariable String studentId){
        List<PaymentResponseDTO> response = paymentService.getPaymentByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByStatus(@PathVariable String status){
        List<PaymentResponseDTO> response = paymentService.getPaymentsByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
