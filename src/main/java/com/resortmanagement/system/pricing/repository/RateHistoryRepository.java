package com.resortmanagement.system.pricing.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.pricing.entity.RateHistory;

@Repository
public interface RateHistoryRepository extends JpaRepository<RateHistory, UUID> {

    @Query("""
        SELECT r FROM RateHistory r
        WHERE r.ratePlan.id = :ratePlanId
        AND :date BETWEEN r.startDate AND r.endDate
    """)
    Optional<RateHistory> findApplicableRate(
            @Param("ratePlanId") UUID ratePlanId,
            @Param("date") LocalDate date
    );
}

