package com.resortmanagement.system.reporting.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortmanagement.system.reporting.entity.ReportMeta;
import com.resortmanagement.system.reporting.service.ReportMetaService;

import jakarta.validation.Valid;

/**
 * ReportMetaController
 * Purpose:
 *  - REST controller for ReportMeta operations
 * Endpoints:
 *  - GET /api/reporting/reports - Get all report metadata
 *  - GET /api/reporting/reports/{id} - Get report metadata by ID
 *  - POST /api/reporting/reports - Create new report metadata
 *  - PUT /api/reporting/reports/{id} - Update report metadata
 *  - DELETE /api/reporting/reports/{id} - Soft-delete report metadata
 */
@RestController
@RequestMapping("/api/reporting/reports")
public class ReportMetaController {

    private final ReportMetaService service;

    public ReportMetaController(ReportMetaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReportMeta>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportMeta> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReportMeta> create(@Valid @RequestBody ReportMeta reportMeta) {
        ReportMeta created = service.save(reportMeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportMeta> update(@PathVariable UUID id, @Valid @RequestBody ReportMeta reportMeta) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reportMeta.setId(id);
        return ResponseEntity.ok(service.save(reportMeta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.softDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
