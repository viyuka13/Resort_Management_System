/**
 * Folio.java
 * Purpose:
 *  - Billing bucket entity; multiple folios per reservation allow split billing.
 * Fields & annotations:
 *  - @Entity @Table("folio")
 *  - id: UUID PK
 *  - reservationId: UUID (nullable for walk-in charges)
 *  - name: String (e.g., "Main Folio", "Incidentals - Guest A")
 *  - bookingGuestId: UUID nullable (if folio is assigned to a specific BookingGuest)
 *  - status enum (OPEN/CLOSED)
 *  - createdAt/updatedAt handled by Auditable -> extend Auditable
 *  - totalAmount computed or stored (BigDecimal) - prefer compute in query or maintain by service
 *  - soft-delete not required; closing is a state change
 * Behavior:
 *  - No heavy logic inside entity.
 *  - Provide equals/hashCode only on id.
 *
 * File: billing/entity/Folio.java
 */
package com.resortmanagement.system.billing.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "folio")
@Getter
@Setter
public class Folio extends Auditable {

    @Id
    @UuidGenerator
    @Column(name = "folio_id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "reservation_id", columnDefinition = "VARCHAR(36)")
    private UUID reservationId;

    @NotBlank
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "booking_guest_id", columnDefinition = "VARCHAR(36)")
    private UUID bookingGuestId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private FolioStatus status = FolioStatus.OPEN;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Folio)) return false;
        Folio folio = (Folio) o;
        return id != null && id.equals(folio.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}