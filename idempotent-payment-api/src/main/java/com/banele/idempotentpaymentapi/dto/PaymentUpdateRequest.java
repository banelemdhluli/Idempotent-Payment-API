package com.banele.idempotentpaymentapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

public class PaymentUpdateRequest {

    @Pattern(
            regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$",
            message = "Request ID must contain letters and numbers only (no symbols), and cannot be numbers only"
    )
    private String requestId;

    @DecimalMin(
            value = "0.01",
            message = "Amount must be greater than 0.01"
    )
    private BigDecimal amount;

    public PaymentUpdateRequest() {}

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
