/*
TODO: RateHistoryController.java
Purpose:
 - Manage seasonal rate overrides associated to RatePlan.
Endpoints:
 - POST /api/v1/rate-plans/{id}/history
 - GET /api/v1/rate-plans/{id}/history
Responsibilities:
 - Ensure RateHistory date ranges are non-overlapping and validate rules.
File: pricing/controller/RateHistoryController.java
*/
package com.resortmanagement.system.pricing.controller;

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

import com.resortmanagement.system.pricing.entity.RateHistory;
import com.resortmanagement.system.pricing.service.RateHistoryService;

@RestController
@RequestMapping("/api/pricing/ratehistories")
public class RateHistoryController {

    private final RateHistoryService service;

    public RateHistoryController(RateHistoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RateHistory>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateHistory> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RateHistory> create(@RequestBody RateHistory entity) {
        // TODO: add validation
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RateHistory> update(@PathVariable Long id, @RequestBody RateHistory entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
