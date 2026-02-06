/*
TODO: MaintenanceRequestController.java
Purpose:
 - Manage maintenance reports and link to RoomBlock to prevent bookings.
Endpoints:
 - POST /api/v1/maintenance -> report issue
 - POST /api/v1/maintenance/{id}/take -> assign engineer
 - POST /api/v1/maintenance/{id}/resolve
Responsibilities:
 - On critical issues create RoomBlock entries to mark room OOO; notify relevant staff via events.
File: room/controller/MaintenanceRequestController.java
*/
package com.resortmanagement.system.room.controller;

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

import com.resortmanagement.system.room.entity.MaintenanceRequest;
import com.resortmanagement.system.room.service.MaintenanceRequestService;

@RestController
@RequestMapping("/api/room/maintenancerequests")
public class MaintenanceRequestController {

    private final MaintenanceRequestService service;

    public MaintenanceRequestController(MaintenanceRequestService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceRequest>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRequest> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MaintenanceRequest> create(@RequestBody MaintenanceRequest entity) {
        // TODO: add validation
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceRequest> update(@PathVariable Long id, @RequestBody MaintenanceRequest entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
