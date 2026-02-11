/*
TODO: ReservationServiceBooking.java
Purpose:
 - Entity linking Reservation to ServiceItem (scheduled service).
Fields:
 - id: UUID
 - reservation: Reservation
 - serviceItem: ServiceItem (ManyToOne)  -- from fnb
 - scheduledAt: Instant
 - quantity: int
 - price: BigDecimal (snapshot)
 - status: enum (SCHEDULED, COMPLETED, CANCELLED)
 - staffAssigned: Employee (nullable)
 - extends Auditable
Rules:
 - Pricing snapshotted at creation.
 - Cancellation policies applied by service layer.
File: booking/entity/ReservationServiceBooking.java
*/
package com.resortmanagement.system.booking.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.billing.entity.Folio;
import com.resortmanagement.system.common.audit.AuditableSoftDeletable;
import com.resortmanagement.system.common.enums.ServiceBookingStatus;
import com.resortmanagement.system.fnb.entity.ServiceItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ReservationServiceBooking extends AuditableSoftDeletable {
    @Id
    @UuidGenerator
    @Column(name = "reservation_service_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @Column(name = "reservation_id", nullable = false)
    private Reservation reservationId;

    @ManyToOne(fetch=FetchType.LAZY)
    @Column(name = "folio_id", nullable = false)
    private Folio folioId;

    @ManyToOne(fetch=FetchType.LAZY)
    @Column(name = "service_item_id", nullable = false)
    private ServiceItem serviceItemId;

    private LocalDateTime scheduledAt;
    private Integer quantity;
    private Double price; // snapshot of service price at booking time

    @Enumerated(EnumType.STRING)
    private ServiceBookingStatus status; // SCHEDULED, COMPLETED, CANCELLED

    private Boolean is_included_in_package; // true if this service is part of a package deal
}
