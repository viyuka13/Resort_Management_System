package com.resortmanagement.system.reporting.repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.reporting.entity.AuditAction;
import com.resortmanagement.system.reporting.entity.AuditLog;

/**
 * AuditLogRepository
 * Purpose:
 *  - Repository for AuditLog entity operations
 * Methods:
 *  - findByTargetEntity: Find audit logs for a specific entity type
 *  - findByTargetId: Find audit logs for a specific entity instance
 *  - findByPerformedBy: Find audit logs by who performed the action
 *  - findByTimestampBetween: Find audit logs within a time range
 */
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {
    
    List<AuditLog> findByTargetEntity(String targetEntity);
    
    List<AuditLog> findByTargetId(UUID targetId);
    
    List<AuditLog> findByPerformedBy(String performedBy);
    
    List<AuditLog> findByTimestampBetween(Instant start, Instant end);
    
    List<AuditLog> findByAction(AuditAction action);
}
