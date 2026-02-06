/*
TODO: RoomTypeController.java
Purpose:
 - Manage room types (Deluxe, Suite) and base info.
Endpoints:
 - POST /api/v1/room-types
 - GET /api/v1/room-types
Responsibilities:
 - RoomTypeService handles base rate and amenities summary; pricing handled by RatePlan.
File: room/controller/RoomTypeController.java
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

import com.resortmanagement.system.room.entity.RoomType;
import com.resortmanagement.system.room.service.RoomTypeService;

@RestController
@RequestMapping("/api/room/roomtypes")
public class RoomTypeController {

    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.service = roomTypeService;
    }

    @GetMapping
    public ResponseEntity<List<RoomType>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> getById(@PathVariable Long id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoomType> create(@RequestBody RoomType entity) {
        // TODO: add validation
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomType> update(@PathVariable Long id, @RequestBody RoomType entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(this.service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
