package com.resortmanagement.system.billing.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.resortmanagement.system.billing.entity.InvoiceStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InvoiceRequest {
    private UUID folioId;
    private UUID reservationId;
    private Instant dueDate;
    @NotNull
    private BigDecimal totalAmount;
    private BigDecimal taxAmount;
    private String currency;
    private InvoiceStatus status; // Optional, default DRAFT
}
