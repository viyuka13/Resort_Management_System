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

import com.resortmanagement.system.billing.entity.AccountLedger;
import com.resortmanagement.system.billing.service.AccountLedgerService;

import jakarta.validation.Valid;

/**
 * AccountLedgerController
 * Purpose:
 *  - REST controller for AccountLedger operations
 * Endpoints:
 *  - GET /api/billing/ledger - Get all ledger accounts
 *  - GET /api/billing/ledger/{id} - Get ledger account by ID
 *  - POST /api/billing/ledger - Create new ledger account
 *  - PUT /api/billing/ledger/{id} - Update ledger account
 *  - DELETE /api/billing/ledger/{id} - Delete ledger account (zero balance only)
 */
@RestController
@RequestMapping("/api/billing/ledger")
public class AccountLedgerController {

    private final AccountLedgerService service;

    public AccountLedgerController(AccountLedgerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AccountLedger>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountLedger> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AccountLedger> create(@Valid @RequestBody AccountLedger ledger) {
        AccountLedger created = service.save(ledger);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountLedger> update(@PathVariable UUID id, @Valid @RequestBody AccountLedger ledger) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ledger.setId(id);
        return ResponseEntity.ok(service.save(ledger));
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
