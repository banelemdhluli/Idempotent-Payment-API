package com.banele.idempotentpaymentapi.dto;

import com.banele.idempotentpaymentapi.model.PaymentStatus;

import java.math.BigDecimal;

public class PaymentResponse {

    // Instance variables

    private String requestId;
    private BigDecimal amount;
    private PaymentStatus status;

    // Constructor
    public PaymentResponse(String requestId, BigDecimal amount, PaymentStatus status) {

        this.requestId = requestId;
        this.amount = amount;
        this.status = status;
    }

    // Getters

    public String getRequestId() {
        return requestId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
