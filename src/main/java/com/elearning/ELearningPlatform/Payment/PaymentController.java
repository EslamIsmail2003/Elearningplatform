package com.elearning.ELearningPlatform.Payment;


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
    public ResponseEntity<PaymentResponseDTO> addPayment(@RequestBody PaymentRequestDTO payment){
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
    public ResponseEntity<PaymentResponseDTO> updatePayment(@RequestBody PaymentRequestDTO updatedValue, @PathVariable String id){
        Optional<PaymentResponseDTO> updated = paymentService.updatePayment(id, updatedValue);
        if (updated.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
