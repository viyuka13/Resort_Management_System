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

import com.resortmanagement.system.billing.entity.Payment;
import com.resortmanagement.system.billing.service.PaymentService;

import jakarta.validation.Valid;

/**
 * PaymentController
 * Purpose:
 *  - REST controller for Payment operations
 * Endpoints:
 *  - GET /api/billing/payments - Get all payments
 *  - GET /api/billing/payments/{id} - Get payment by ID
 *  - POST /api/billing/payments - Create new payment
 *  - POST /api/billing/payments/{id}/process - Process payment (PENDING -> SUCCESS/FAILED)
 *  - DELETE /api/billing/payments/{id} - Delete payment (PENDING/FAILED only)
 */
@RestController
@RequestMapping("/api/billing/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Payment> create(@Valid @RequestBody Payment payment) {
        Payment created = service.save(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<Payment> processPayment(
            @PathVariable UUID id,
            @RequestParam boolean success,
            @RequestParam(required = false) String providerResponse) {
        Payment processed = service.processPayment(id, success, providerResponse);
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
