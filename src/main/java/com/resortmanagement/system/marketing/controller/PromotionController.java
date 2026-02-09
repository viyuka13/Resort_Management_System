package com.resortmanagement.system.marketing.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resortmanagement.system.marketing.dto.PromotionDTO;
import com.resortmanagement.system.marketing.service.PromotionService;

@RestController
@RequestMapping("/api/marketing/promotions")
public class PromotionController {

    private final PromotionService service;

    public PromotionController(PromotionService promotionService) {
        this.service = promotionService;
    }

    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<PromotionDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.service.findAll(org.springframework.data.domain.PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDTO> getById(@PathVariable UUID id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PromotionDTO> create(@RequestBody PromotionDTO dto) {
        if (dto.getCode() == null || dto.getValidFrom() == null || dto.getValidTo() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionDTO> update(@PathVariable UUID id, @RequestBody PromotionDTO dto) {
        return ResponseEntity.ok(this.service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/calculate-discount")
    public ResponseEntity<BigDecimal> calculateDiscount(@PathVariable UUID id, @RequestParam BigDecimal price) {
        if (price == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.service.calculateDiscount(id, price));
    }
}
