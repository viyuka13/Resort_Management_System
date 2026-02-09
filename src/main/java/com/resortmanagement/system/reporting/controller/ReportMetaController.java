package com.resortmanagement.system.reporting.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resortmanagement.system.reporting.dto.ReportMetaRequest;
import com.resortmanagement.system.reporting.dto.ReportMetaResponse;
import com.resortmanagement.system.reporting.entity.ReportMeta;
import com.resortmanagement.system.reporting.mapper.ReportingMapper;
import com.resortmanagement.system.reporting.service.ReportMetaService;
import java.util.stream.Collectors;

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
 */
@RestController
@RequestMapping("/api/reporting/reports")
public class ReportMetaController {

    private final ReportMetaService service;

    public ReportMetaController(ReportMetaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReportMetaResponse>> getAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(ReportingMapper::toResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportMetaResponse> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ReportingMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReportMetaResponse> create(@Valid @RequestBody ReportMetaRequest request) {
        ReportMeta reportMeta = ReportingMapper.toEntity(request);
        ReportMeta created = service.save(reportMeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReportingMapper.toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportMetaResponse> update(@PathVariable UUID id, @Valid @RequestBody ReportMetaRequest request) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ReportMeta reportMeta = ReportingMapper.toEntity(request);
        reportMeta.setId(id);
        ReportMeta updated = service.save(reportMeta);
        return ResponseEntity.ok(ReportingMapper.toResponse(updated));
    }
}
