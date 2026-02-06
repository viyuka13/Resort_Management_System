/*
TODO: AuditLogController.java
Purpose:
 - Provide read-only access to audit logs for compliance (admin).
Endpoints:
 - GET /api/v1/audit-logs?entity=Reservation&entityId=...
Responsibilities:
 - Secure endpoint (ROLE_AUDITOR, ROLE_ADMIN).
 - Use paged responses and filtering by date, user, action.
File: reporting/controller/AuditLogController.java
*/
package com.resortmanagement.system.reporting.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortmanagement.system.reporting.entity.AuditLog;
import com.resortmanagement.system.reporting.service.AuditLogService;

@RestController
@RequestMapping("/api/reporting/auditlogs")
public class AuditLogController {

    private final AuditLogService service;

    public AuditLogController(AuditLogService auditLogService) {
        this.service = auditLogService;
    }

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getById(@PathVariable Long id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuditLog> create(@RequestBody AuditLog entity) {
        // TODO: add validation
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditLog> update(@PathVariable Long id, @RequestBody AuditLog entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(this.service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
