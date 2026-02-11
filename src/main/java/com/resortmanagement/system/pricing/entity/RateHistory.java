/*
RateHistory.java
Purpose:
 - Seasonal price entries for a RatePlan.
Fields:
 - id UUID
 - ratePlan: RatePlan (ManyToOne)
 - price BigDecimal
 - seasonName String
 - startDate LocalDate
 - endDate LocalDate
 - extends Auditable
Rules:
 - Prevent overlapping ranges for the same rate plan (validate in service).
File: pricing/entity/RateHistory.java
*/
package com.resortmanagement.system.pricing.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RateHistory extends Auditable {
    @Id
    @UuidGenerator
    @Column(name = "rate_history_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "rate_plan_id", nullable = false)
    private RatePlan ratePlanId;

    private Double price;
    
    public Double getPrice() {
        return this.price;
    }
    private String seasonName;
    private LocalDate startDate;
    private LocalDate endDate;
}