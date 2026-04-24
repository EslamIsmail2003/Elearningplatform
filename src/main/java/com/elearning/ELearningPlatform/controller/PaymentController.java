package com.elearning.ELearningPlatform.controller;


import com.elearning.ELearningPlatform.model.Payment;
import com.elearning.ELearningPlatform.service.PaymentService;
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
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment){
        Payment saved = paymentService.addPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments(){
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.status(HttpStatus.OK).body(payments);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable String id){
        Optional<Payment> payments = paymentService.getPaymentById(id);
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
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment updatedValue, @PathVariable String id){
        Optional<Payment> updated = paymentService.updatePayment(id, updatedValue);
        if (updated.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
