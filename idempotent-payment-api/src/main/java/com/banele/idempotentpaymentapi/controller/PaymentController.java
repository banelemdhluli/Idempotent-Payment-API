package com.banele.idempotentpaymentapi.controller;

import com.banele.idempotentpaymentapi.dto.PaymentRequest;
import com.banele.idempotentpaymentapi.dto.PaymentResponse;
import com.banele.idempotentpaymentapi.service.PaymentService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    // Instance variable

    private final PaymentService service;

    // Constructor Dependency Injection
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response =
                service.processPayment(request);

        return ResponseEntity.ok(response);
    }
}

