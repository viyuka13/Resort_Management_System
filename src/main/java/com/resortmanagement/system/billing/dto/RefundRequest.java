package com.resortmanagement.system.billing.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefundRequest {
    @NotNull
    private UUID paymentId;
    @NotNull
    private BigDecimal amount;
    private String reason;
}
