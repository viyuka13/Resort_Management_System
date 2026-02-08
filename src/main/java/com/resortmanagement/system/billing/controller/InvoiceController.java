package com.resortmanagement.system.billing.controller;

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

import com.resortmanagement.system.billing.entity.Invoice;
import com.resortmanagement.system.billing.service.InvoiceService;

import jakarta.validation.Valid;

/**
 * InvoiceController
 * Purpose:
 *  - REST controller for Invoice operations
 * Endpoints:
 *  - GET /api/billing/invoices - Get all invoices
 *  - GET /api/billing/invoices/{id} - Get invoice by ID
 *  - POST /api/billing/invoices - Create new invoice
 *  - PUT /api/billing/invoices/{id} - Update invoice
 *  - DELETE /api/billing/invoices/{id} - Delete invoice (DRAFT only)
 *  - POST /api/billing/invoices/{id}/issue - Issue invoice (DRAFT -> ISSUED)
 */
@RestController
@RequestMapping("/api/billing/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@Valid @RequestBody Invoice invoice) {
        Invoice created = service.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> update(@PathVariable UUID id, @Valid @RequestBody Invoice invoice) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        invoice.setId(id);
        return ResponseEntity.ok(service.save(invoice));
    }

    @PostMapping("/{id}/issue")
    public ResponseEntity<Invoice> issueInvoice(@PathVariable UUID id) {
        Invoice issued = service.issueInvoice(id);
        return ResponseEntity.ok(issued);
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
