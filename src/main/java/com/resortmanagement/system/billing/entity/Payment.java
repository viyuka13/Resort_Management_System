/**
 * Payment.java
 * Purpose:
 *  - Records payment attempts and results for invoices.
 * Fields:
 *  - id: UUID
 *  - invoiceId: UUID (reference to Invoice)
 *  - reservationId: UUID (nullable)
 *  - guestId: UUID
 *  - amount: BigDecimal
 *  - method: enum (CARD, UPI, CASH, WALLET, BANK_TRANSFER)
 *  - transactionRef: String (PSP reference)
 *  - status: PaymentStatus (PENDING, SUCCESS, FAILED, REFUNDED)
 *  - providerResponse: JSON/string for PSP raw response
 *  - processedAt: Instant
 *  - extends Auditable (important for PCI/compliance trace)
 * Notes:
 *  - Do NOT store card PAN/CVV. Use PSP tokenization.
 *  - Payment creation and invoice update should be handled in a single transactional service method with idempotency.
 *
 * File: billing/entity/Payment.java
 */
package com.resortmanagement.system.billing.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.Auditable;
import com.resortmanagement.system.common.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment extends Auditable {

    @Id
    @UuidGenerator
    @Column(name = "payment_id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "invoice_id", columnDefinition = "VARCHAR(36)", nullable = false)
    private UUID invoiceId;

    @Column(name = "reservation_id", columnDefinition = "VARCHAR(36)")
    private UUID reservationId;

    @NotNull
    @Column(name = "guest_id", columnDefinition = "VARCHAR(36)", nullable = false)
    private UUID guestId;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    @Column(name = "transaction_ref", length = 100)
    private String transactionRef;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "provider_response", columnDefinition = "TEXT")
    private String providerResponse;

    @Column(name = "processed_at")
    private Instant processedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return id != null && id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}