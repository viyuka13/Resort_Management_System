package com.resortmanagement.system.billing.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.resortmanagement.system.billing.entity.RefundStatus;

import lombok.Data;

@Data
public class RefundResponse {
    private UUID id;
    private UUID paymentId;
    private BigDecimal amount;
    private String reason;
    private String processedBy;
    private Instant processedAt;
    private RefundStatus status;
    private String providerRefundRef;
}
