/*
TODO: LoyaltyMemberController.java
Purpose:
 - Manage loyalty program members and points.
Endpoints:
 - POST /api/v1/loyalty -> enroll member
 - GET /api/v1/loyalty/{id}
 - POST /api/v1/loyalty/{id}/adjust-points
Responsibilities:
 - Keep points arithmetic in service; maintain transactional integrity.
 - Protect endpoints for admin when adjusting points.
File: marketing/controller/LoyaltyMemberController.java
*/
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

import com.resortmanagement.system.marketing.entity.LoyaltyMember;
import com.resortmanagement.system.marketing.service.LoyaltyMemberService;

@RestController
@RequestMapping("/api/marketing/loyalty_members")
public class LoyaltyMemberController {

    private final LoyaltyMemberService service;

    public LoyaltyMemberController(LoyaltyMemberService loyaltyMemberService) {
        this.service = loyaltyMemberService;
    }

    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<LoyaltyMember>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.findAll(org.springframework.data.domain.PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoyaltyMember> getById(@PathVariable UUID id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LoyaltyMember> create(@RequestBody LoyaltyMember entity) {
        if (entity.getGuest() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoyaltyMember> update(@PathVariable UUID id, @RequestBody LoyaltyMember entity) {
        return ResponseEntity.ok(service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/award-points")
    public ResponseEntity<Void> awardPoints(@PathVariable UUID id, @RequestParam BigDecimal amount) {
        service.awardPoints(id, amount);
        return ResponseEntity.ok().build();
    }
}
