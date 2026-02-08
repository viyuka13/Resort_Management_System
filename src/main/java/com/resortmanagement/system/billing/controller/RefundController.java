package com.resortmanagement.system.billing.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortmanagement.system.billing.entity.Refund;
import com.resortmanagement.system.billing.service.RefundService;

import jakarta.validation.Valid;

/**
 * RefundController
 * Purpose:
 *  - REST controller for Refund operations
 * Endpoints:
 *  - GET /api/billing/refunds - Get all refunds
 *  - GET /api/billing/refunds/{id} - Get refund by ID
 *  - POST /api/billing/refunds - Create new refund request
 *  - POST /api/billing/refunds/{id}/process - Process refund (REQUESTED/PROCESSING -> SUCCESS/FAILED)
 *  - DELETE /api/billing/refunds/{id} - Delete refund (REQUESTED/FAILED only)
 */
@RestController
@RequestMapping(" /api/billing/refunds")
public class RefundController {

    private final RefundService service;

    public RefundController(RefundService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Refund>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refund> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Refund> create(@Valid @RequestBody Refund refund) {
        Refund created = service.save(refund);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<Refund> processRefund(
            @PathVariable UUID id,
            @RequestParam boolean success,
            @RequestParam(required = false) String providerRefundRef) {
        Refund processed = service.processRefund(id, success, providerRefundRef);
        return ResponseEntity.ok(processed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
