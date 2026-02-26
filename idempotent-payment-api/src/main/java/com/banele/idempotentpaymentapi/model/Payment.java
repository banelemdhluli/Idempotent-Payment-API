package com.banele.idempotentpaymentapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity

// Table name
@Table(
        name = "payments",
        uniqueConstraints = @UniqueConstraint(columnNames = "request_id")
)
public class Payment {

    // Instance variables and their annotations

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "request_id", nullable = false, unique = true)
    private String requestId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Required by JPA
    public Payment() {
    }

    public Payment(String requestId, BigDecimal amount, PaymentStatus status) {
        this.requestId = requestId;
        this.amount = amount;
        this.status = status;
    }

    // ✅ FIXED CONSTRUCTOR
    public Payment(String requestId, BigDecimal amount) {
        this.requestId = requestId;
        this.amount = amount;
        this.status = PaymentStatus.PENDING; // Prevents NULL status (fixes 500 error)
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

