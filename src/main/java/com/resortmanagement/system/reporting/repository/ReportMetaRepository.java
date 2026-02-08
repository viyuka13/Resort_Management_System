package com.resortmanagement.system.reporting.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import com.resortmanagement.system.reporting.entity.ReportMeta;

/**
 * ReportMetaRepository
 * Purpose:
 *  - Repository for ReportMeta entity operations
 *  - Extends SoftDeleteRepository for soft-delete support
 * Methods:
 *  - findByName: Find report metadata by name
 *  - findByOwnerId: Find all reports owned by a specific user
 */
public interface ReportMetaRepository extends SoftDeleteRepository<ReportMeta, UUID> {
    
    Optional<ReportMeta> findByName(String name);
    
    List<ReportMeta> findByOwnerId(UUID ownerId);
}
