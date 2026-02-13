package com.banele.idempotentpaymentapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PaymentRequest {

    // Instance variables and their annotations

    @NotBlank
    private String requestId;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    public PaymentRequest() {
    }

    //  Getters & Setters

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
}
