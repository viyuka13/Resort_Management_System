/*
TODO: CommunicationController.java
Purpose:
 - Log or send communications (email/SMS) to guests; store sent communications for audit.
Endpoints:
 - POST /api/v1/communications/send -> send message (email/sms) and store record
 - GET /api/v1/communications?guestId=...
Responsibilities:
 - Use Integration module (email/SMS provider) to actually send messages.
 - Store communication in DB for audit trail (Communication entity).
File: support/controller/CommunicationController.java
*/
package com.resortmanagement.system.support.controller;


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

import com.resortmanagement.system.support.entity.Communication;
import com.resortmanagement.system.support.service.CommunicationService;

@RestController
@RequestMapping("/api/support/communications")
public class CommunicationController {

    private final CommunicationService service;

    public CommunicationController(CommunicationService communicationService) {
        this.service = communicationService;
    }

    @GetMapping
    public ResponseEntity<List<Communication>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Communication> getById(@PathVariable Long id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Communication> create(@RequestBody Communication entity) {
        // TODO: add validation
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Communication> update(@PathVariable Long id, @RequestBody Communication entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(this.service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
