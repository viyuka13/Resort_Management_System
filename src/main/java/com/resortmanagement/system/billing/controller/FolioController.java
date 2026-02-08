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

import com.resortmanagement.system.billing.entity.Folio;
import com.resortmanagement.system.billing.service.FolioService;

import jakarta.validation.Valid;

/**
 * FolioController
 * Purpose:
 *  - REST controller for Folio operations
 * Endpoints:
 *  - GET /api/billing/folios - Get all folios
 *  - GET /api/billing/folios/{id} - Get folio by ID
 *  - POST /api/billing/folios - Create new folio
 *  - PUT /api/billing/folios/{id} - Update folio
 *  - DELETE /api/billing/folios/{id} - Delete folio
 *  - POST /api/billing/folios/{id}/close - Close folio (state transition)
 */
@RestController
@RequestMapping("/api/billing/folios")
public class FolioController {

    private final FolioService service;

    public FolioController(FolioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Folio>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Folio> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Folio> create(@Valid @RequestBody Folio folio) {
        Folio created = service.save(folio);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Folio> update(@PathVariable UUID id, @Valid @RequestBody Folio folio) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        folio.setId(id);
        return ResponseEntity.ok(service.save(folio));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Folio> closeFolio(@PathVariable UUID id) {
        Folio closed = service.closeFolio(id);
        return ResponseEntity.ok(closed);
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
