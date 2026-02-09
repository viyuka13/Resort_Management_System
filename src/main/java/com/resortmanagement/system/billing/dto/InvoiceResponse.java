package com.resortmanagement.system.billing.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.resortmanagement.system.billing.entity.InvoiceStatus;

import lombok.Data;

@Data
public class InvoiceResponse {
    private UUID id;
    private UUID folioId;
    private UUID reservationId;
    private Instant issueDate;
    private Instant dueDate;
    private BigDecimal totalAmount;
    private BigDecimal taxAmount;
    private InvoiceStatus status;
    private String currency;
    private List<PaymentResponse> payments;
}
