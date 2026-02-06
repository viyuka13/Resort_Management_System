/*
TODO: RatePlanController.java
Purpose:
 - CRUD for RatePlan.
Endpoints:
 - POST /api/v1/rate-plans
 - GET /api/v1/rate-plans/{id}
Responsibilities:
 - RatePlan defines cancellation policy, base price, min/max stay, refundable flag.
File: pricing/controller/RatePlanController.java
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

import com.resortmanagement.system.pricing.entity.RatePlan;
import com.resortmanagement.system.pricing.service.RatePlanService;

@RestController
@RequestMapping("/api/pricing/rateplans")
public class RatePlanController {

    private final RatePlanService service;

    public RatePlanController(RatePlanService ratePlanService) {
        this.service = ratePlanService;
    }

    @GetMapping
    public ResponseEntity<List<RatePlan>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatePlan> getById(@PathVariable Long id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RatePlan> create(@RequestBody RatePlan entity) {
        // TODO: add validation
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatePlan> update(@PathVariable Long id, @RequestBody RatePlan entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(this.service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
