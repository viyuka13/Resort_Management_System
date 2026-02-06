package com.resortmanagement.system.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.pricing.entity.RatePlan;

@Repository
public interface RatePlanRepository extends JpaRepository<RatePlan, Long> {
    // TODO: add custom queries if needed
}
