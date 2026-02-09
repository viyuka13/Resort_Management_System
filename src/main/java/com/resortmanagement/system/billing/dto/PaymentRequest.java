package com.resortmanagement.system.billing.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.resortmanagement.system.billing.entity.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotNull
    private UUID invoiceId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private PaymentMethod paymentMethod;
    private String transactionRef;
    private String providerResponse;
}
