package com.resortmanagement.system.pricing.repository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.pricing.entity.RatePlan;

@Repository
public interface RatePlanRepository extends JpaRepository<RatePlan, UUID> {

    Optional<RatePlan> findByNameAndDeletedFalse(String name);

    List<RatePlan> findByDeletedFalse();
}

