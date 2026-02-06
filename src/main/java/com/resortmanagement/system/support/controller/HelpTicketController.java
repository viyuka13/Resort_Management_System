/*
TODO: HelpTicketController.java
Purpose:
 - Internal helpdesk or guest requests management.
Endpoints:
 - POST /api/v1/help-tickets -> create ticket
 - GET /api/v1/help-tickets/{id}
 - POST /api/v1/help-tickets/{id}/assign
Responsibilities:
 - Tickets link to reservation and guest; triage and assign to staff.
 - Service handles priority and SLA logic, sends notifications.
File: support/controller/HelpTicketController.java
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

import com.resortmanagement.system.support.entity.HelpTicket;
import com.resortmanagement.system.support.service.HelpTicketService;

@RestController
@RequestMapping("/api/support/helptickets")
public class HelpTicketController {

    private final HelpTicketService service;

    public HelpTicketController(HelpTicketService helpTicketService) {
        this.service = helpTicketService;
    }

    @GetMapping
    public ResponseEntity<List<HelpTicket>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HelpTicket> getById(@PathVariable Long id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HelpTicket> create(@RequestBody HelpTicket entity) {
        // TODO: add validation
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HelpTicket> update(@PathVariable Long id, @RequestBody HelpTicket entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(this.service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
