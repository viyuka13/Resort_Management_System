package com.resortmanagement.system.billing.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.resortmanagement.system.billing.entity.FolioStatus;

import lombok.Data;

@Data
public class FolioResponse {
    private UUID id;
    private UUID reservationId;
    private String name;
    private UUID bookingGuestId;
    private FolioStatus status;
    private BigDecimal totalAmount;
    private List<InvoiceResponse> invoices;
}
