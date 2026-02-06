package com.resortmanagement.system.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.reporting.entity.ReportMeta;

@Repository
public interface ReportMetaRepository extends JpaRepository<ReportMeta, Long> {
    // TODO: add custom queries if needed
}
