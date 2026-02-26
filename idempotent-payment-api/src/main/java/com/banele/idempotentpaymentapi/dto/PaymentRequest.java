package com.banele.idempotentpaymentapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

public class PaymentRequest {

    @Pattern(
            regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$",
            message = "Request ID must contain letters and numbers combined (no symbols,no numbers only), and cannot be empty"
    )
    private String requestId;

    @DecimalMin(
            value = "0.01",
            message = "Amount must be greater than 0.01"
    )
    private BigDecimal amount;

    public PaymentRequest() {}

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
