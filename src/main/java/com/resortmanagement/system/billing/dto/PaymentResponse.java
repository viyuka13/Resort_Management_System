package com.resortmanagement.system.billing.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.resortmanagement.system.common.enums.PaymentStatus;
import com.resortmanagement.system.billing.entity.PaymentMethod;

import lombok.Data;

@Data
public class PaymentResponse {
    private UUID id;
    private UUID invoiceId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String transactionRef;
    private PaymentStatus status;
    private String providerResponse;
    private Instant processedAt;
}
