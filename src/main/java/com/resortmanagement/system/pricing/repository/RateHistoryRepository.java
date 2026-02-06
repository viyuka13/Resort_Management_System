package com.resortmanagement.system.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.pricing.entity.RateHistory;

@Repository
public interface RateHistoryRepository extends JpaRepository<RateHistory, Long> {
    // TODO: add custom queries if needed
}
