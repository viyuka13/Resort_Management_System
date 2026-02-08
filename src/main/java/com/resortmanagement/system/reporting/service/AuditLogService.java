package com.resortmanagement.system.reporting.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.reporting.entity.AuditAction;
import com.resortmanagement.system.reporting.entity.AuditLog;
import com.resortmanagement.system.reporting.repository.AuditLogRepository;

/**
 * AuditLogService
 * Purpose:
 *  - Service layer for AuditLog entity operations
 *  - Provides utility methods for logging audit events
 * Business Logic:
 *  - log: Convenience method to create audit log entries
 *  - All audit logs are append-only (no updates or deletes)
 */
@Service
@Transactional
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<AuditLog> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<AuditLog> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<AuditLog> findByTargetEntity(String targetEntity) {
        return repository.findByTargetEntity(targetEntity);
    }

    @Transactional(readOnly = true)
    public List<AuditLog> findByTargetId(UUID targetId) {
        return repository.findByTargetId(targetId);
    }

    @Transactional(readOnly = true)
    public List<AuditLog> findByPerformedBy(String performedBy) {
        return repository.findByPerformedBy(performedBy);
    }

    @Transactional(readOnly = true)
    public List<AuditLog> findByTimestampBetween(Instant start, Instant end) {
        return repository.findByTimestampBetween(start, end);
    }

    public AuditLog save(AuditLog auditLog) {
        // Audit logs are append-only, so always create new
        if (auditLog.getTimestamp() == null) {
            auditLog.setTimestamp(Instant.now());
        }
        return repository.save(auditLog);
    }

    /**
     * Convenience method to log an audit event
     */
    public AuditLog log(String targetEntity, UUID targetId, AuditAction action, 
                        String performedBy, String changesJson, String reason) {
        AuditLog auditLog = new AuditLog();
        auditLog.setTargetEntity(targetEntity);
        auditLog.setTargetId(targetId);
        auditLog.setAction(action);
        auditLog.setPerformedBy(performedBy);
        auditLog.setChangesJson(changesJson);
        auditLog.setReason(reason);
        auditLog.setTimestamp(Instant.now());
        return repository.save(auditLog);
    }
}
