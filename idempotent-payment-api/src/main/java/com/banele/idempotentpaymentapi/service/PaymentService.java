package com.banele.idempotentpaymentapi.service;

import com.banele.idempotentpaymentapi.dto.PaymentRequest;
import com.banele.idempotentpaymentapi.dto.PaymentResponse;
import com.banele.idempotentpaymentapi.dto.PaymentUpdateRequest;
import com.banele.idempotentpaymentapi.exception.ResourceNotFoundException;
import com.banele.idempotentpaymentapi.model.Payment;
import com.banele.idempotentpaymentapi.model.PaymentStatus;
import com.banele.idempotentpaymentapi.repository.PaymentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    // GET

    public List<PaymentResponse> getAllPayments() {

        return repository.findAll()
                .stream()
                .map(payment -> new PaymentResponse(
                        payment.getRequestId(),
                        payment.getAmount(),
                        payment.getStatus().name()
                ))
                .toList();
    }


    // POST


    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {

        Optional<Payment> existingPayment =
                repository.findByRequestId(request.getRequestId());

        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();

            return new PaymentResponse(
                    payment.getRequestId(),
                    payment.getAmount(),
                    payment.getStatus().name()
            );
        }

        Payment newPayment = new Payment(
                request.getRequestId(),
                request.getAmount()
        );

        repository.save(newPayment);

        return new PaymentResponse(
                newPayment.getRequestId(),
                newPayment.getAmount(),
                newPayment.getStatus().name()
        );
    }


    // PUT (Full Update)

    @Transactional
    public PaymentResponse updatePayment(String id, PaymentRequest request) {

        Payment payment = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found with id: " + id
                        )
                );

        // Full replacement
        payment.setRequestId(request.getRequestId());
        payment.setAmount(request.getAmount());

        if (payment.getStatus() == null) {
            payment.setStatus(PaymentStatus.PENDING);
        }

        repository.save(payment);

        return new PaymentResponse(
                payment.getRequestId(),
                payment.getAmount(),
                payment.getStatus().name()
        );
    }



    // PATCH (Partial Update)

    @Transactional
    public PaymentResponse patchPayment(String id, PaymentUpdateRequest request) {

        Payment payment = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found with id: " + id
                        )
                );

        if (request.getRequestId() != null) {
            payment.setRequestId(request.getRequestId());
        }

        if (request.getAmount() != null) {
            payment.setAmount(request.getAmount());
        }

        if (payment.getStatus() == null) {
            payment.setStatus(PaymentStatus.PENDING);
        }

        repository.save(payment);

        return new PaymentResponse(
                payment.getRequestId(),
                payment.getAmount(),
                payment.getStatus().name()
        );
    }

    // DELETE


    @Transactional
    public void deletePayment(String id) {

        Payment payment = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found with id: " + id
                        )
                );

        repository.delete(payment);
    }
}
