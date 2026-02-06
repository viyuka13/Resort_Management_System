/*
TODO: ReportMetaController.java
Purpose:
 - CRUD for scheduled report metadata (name, schedule, owner).
Endpoints:
 - POST /api/v1/reports
 - GET /api/v1/reports/{id}
Responsibilities:
 - Schedule report execution with a scheduler (Quartz or Spring Task Scheduler).
File: reporting/controller/ReportMetaController.java
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

import com.resortmanagement.system.reporting.entity.ReportMeta;
import com.resortmanagement.system.reporting.service.ReportMetaService;

@RestController
@RequestMapping("/api/reporting/reportmeta")
public class ReportMetaController {

    private final ReportMetaService service;

    public ReportMetaController(ReportMetaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReportMeta>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportMeta> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReportMeta> create(@RequestBody ReportMeta entity) {
        // TODO: add validation
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportMeta> update(@PathVariable Long id, @RequestBody ReportMeta entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
