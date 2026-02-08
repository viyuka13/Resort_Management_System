package com.resortmanagement.system.reporting.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortmanagement.system.reporting.entity.AuditLog;
import com.resortmanagement.system.reporting.service.AuditLogService;

/**
 * AuditLogController
 * Purpose:
 *  - REST controller for AuditLog read-only operations
 * Endpoints:
 *  - GET /api/reporting/audit-logs - Get all audit logs
 *  - GET /api/reporting/audit-logs/{id} - Get audit log by ID
 *  - GET /api/reporting/audit-logs/entity/{entityName} - Get audit logs for specific entity type
 *  - GET /api/reporting/audit-logs/target/{targetId} - Get audit logs for specific entity instance
 * Note:
 *  - Audit logs are read-only (no POST/PUT/DELETE)
 *  - Creation happens automatically in service layers of other domains
 */
@RestController
@RequestMapping("/api/reporting/audit-logs")
public class AuditLogController {

    private final AuditLogService service;

    public AuditLogController(AuditLogService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/entity/{entityName}")
    public ResponseEntity<List<AuditLog>> getByEntity(@PathVariable String entityName) {
        return ResponseEntity.ok(service.findByTargetEntity(entityName));
    }

    @GetMapping("/target/{targetId}")
    public ResponseEntity<List<AuditLog>> getByTargetId(@PathVariable UUID targetId) {
        return ResponseEntity.ok(service.findByTargetId(targetId));
    }

    @GetMapping("/user/{performedBy}")
    public ResponseEntity<List<AuditLog>> getByPerformedBy(@PathVariable String performedBy) {
        return ResponseEntity.ok(service.findByPerformedBy(performedBy));
    }
}
