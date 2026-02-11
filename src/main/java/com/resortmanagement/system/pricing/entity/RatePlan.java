/*
RatePlan.java
Purpose:
 - Rate plan that ties base price to room types and defines cancellation policy.
Fields:
 - id UUID
 - name String
 - description String
 - basePrice BigDecimal
 - cancellationPolicy String (or structured)
 - isRefundable boolean
 - minStayNights int
 - maxStayNights int
 - validFrom, validTo optional
 - extends Auditable
Notes:
 - Pricing calculation uses RateHistory + RatePlan + promotions -> ReservationDailyRate created by BookingService.
File: pricing/entity/RatePlan.java
*/
package com.resortmanagement.system.pricing.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RatePlan extends Auditable {
    @Id
    @UuidGenerator
    @Column(name = "rate_plan_id", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String description;
    private Double basePrice;
    private String cancellationPolicy; // e.g. "Free cancellation until 24h before check-in"
    private Boolean isRefundable;
    private Integer minStayNights;
    private Integer maxStayNights;

    // Link to RoomType
    private UUID roomTypeId;

    private LocalDate validFrom;
    private LocalDate validTo;
}