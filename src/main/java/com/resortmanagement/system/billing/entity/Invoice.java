/**
 * Invoice.java
 * Purpose:
 *  - Persisted financial document generated from folio charges.
 * Fields:
 *  - id: UUID
 *  - folioId: UUID (reference to Folio)
 *  - reservationId: UUID (optional)
 *  - guestId: UUID
 *  - issueDate: Instant
 *  - dueDate: Instant
 *  - totalAmount: BigDecimal
 *  - taxAmount: BigDecimal
 *  - status enum (DRAFT, ISSUED, PAID, PARTIALLY_PAID, REFUNDED, CANCELLED)
 *  - currency String
 *  - extends Auditable (must track who issued)
 *  - store request/response snapshot JSON if needed (for dispute)
 * Notes:
 *  - Immutable once ISSUED except for credit/refund operations stored separately.
 *  - Use InvoiceService to compute totals; never compute in controllers.
 *
 * File: billing/entity/Invoice.java
 */
package com.resortmanagement.system.billing.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoice")
@Getter
@Setter
public class Invoice extends Auditable {

    @Id
    @UuidGenerator
    @Column(name = "invoice_id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "folio_id", columnDefinition = "VARCHAR(36)")
    private UUID folioId;

    @Column(name = "reservation_id", columnDefinition = "VARCHAR(36)")
    private UUID reservationId;

    @NotNull
    @Column(name = "guest_id", columnDefinition = "VARCHAR(36)", nullable = false)
    private UUID guestId;

    @NotNull
    @Column(name = "issue_date", nullable = false)
    private Instant issueDate;

    @Column(name = "due_date")
    private Instant dueDate;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "tax_amount", precision = 10, scale = 2)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private InvoiceStatus status = InvoiceStatus.DRAFT;

    @Column(name = "currency", length = 3)
    private String currency = "INR";

    @Version
    @Column(name = "version")
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return id != null && id.equals(invoice.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}