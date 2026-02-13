package com.banele.idempotentpaymentapi.repository;

import com.banele.idempotentpaymentapi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// It is the data access layer that connects your service
// To the database and supports idempotent payment lookups.

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    Optional<Payment> findByRequestId(String requestId);
}
