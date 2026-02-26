package com.banele.idempotentpaymentapi.controller;

import com.banele.idempotentpaymentapi.dto.PaymentRequest;
import com.banele.idempotentpaymentapi.dto.PaymentResponse;
import com.banele.idempotentpaymentapi.dto.PaymentUpdateRequest;
import com.banele.idempotentpaymentapi.service.PaymentService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Table name
@RequestMapping("/payments")
public class PaymentController {

    // Instance variable

    private final PaymentService service;

    // Constructor Dependency Injection
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // GET

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {

        List<PaymentResponse> payments = service.getAllPayments();
        return ResponseEntity.ok(payments);
    }



    // POST

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response = service.processPayment(request);
        return ResponseEntity.ok(response);
    }


    // PUT (Full Update)

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updatePayment(
            @PathVariable String id,
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response = service.updatePayment(id, request);
        return ResponseEntity.ok(response);
    }

    // PATCH (Partial Update)

    @PatchMapping("/{id}")
    public ResponseEntity<PaymentResponse> patchPayment(
            @PathVariable String id,
            @Valid @RequestBody PaymentUpdateRequest request) {

        PaymentResponse response =
                service.patchPayment(id, request);

        return ResponseEntity.ok(response);
    }

    // DELETE


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable String id) {

        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}





