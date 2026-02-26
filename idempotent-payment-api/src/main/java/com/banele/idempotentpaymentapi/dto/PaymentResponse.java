package com.banele.idempotentpaymentapi.dto;

import java.math.BigDecimal;

public class PaymentResponse {

    // Instance variables

    private String requestId;
    private BigDecimal amount;
    private String status;

    // Constructor
    public PaymentResponse(String requestId, BigDecimal amount, String status) {

        this.requestId = requestId;
        this.amount = amount;
        this.status = status;
    }

    // Getters

    public String getRequestId() { return requestId; }
    public BigDecimal getAmount() { return amount; }
    public String getStatus() { return status; }
}

