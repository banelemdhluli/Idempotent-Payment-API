package com.banele.idempotentpaymentapi.service;

import com.banele.idempotentpaymentapi.dto.PaymentRequest;
import com.banele.idempotentpaymentapi.dto.PaymentResponse;
import com.banele.idempotentpaymentapi.model.Payment;
import com.banele.idempotentpaymentapi.model.PaymentStatus;
import com.banele.idempotentpaymentapi.repository.PaymentRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PaymentService {

    // Instance variable

    private final PaymentRepository repository;

    // Constructor Dependency Injection
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    // Checks if a payment with the same requestId already exists and returns it
    // otherwise it creates and saves a new payment

    public PaymentResponse processPayment(PaymentRequest request) {

        Optional<Payment> existing =
                repository.findByRequestId(request.getRequestId());

        if (existing.isPresent()) {
            Payment p = existing.get();
            return new PaymentResponse(
                    p.getRequestId(),
                    p.getAmount(),
                    p.getStatus()
            );
        }

        Payment payment = new Payment();
        payment.setRequestId(request.getRequestId());
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.PENDING);

        try {
            repository.save(payment);
            payment.setStatus(PaymentStatus.SUCCESS);

        } catch (DataIntegrityViolationException ex) {

            Payment saved =
                    repository.findByRequestId(request.getRequestId())
                            .orElseThrow();

            return new PaymentResponse(
                    saved.getRequestId(),
                    saved.getAmount(),
                    saved.getStatus()
            );
        }

        return new PaymentResponse(
                payment.getRequestId(),
                payment.getAmount(),
                payment.getStatus()
        );
    }
}

