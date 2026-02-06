/*
TODO: HousekeepingTaskController.java
Purpose:
 - Manage housekeeping tasks: schedule cleanings, mark completed.
Endpoints:
 - POST /api/v1/housekeeping/tasks
 - GET /api/v1/rooms/{roomId}/housekeeping
Responsibilities:
 - Create tasks when reservation checks out; assign staff; update room status.
File: room/controller/HousekeepingTaskController.java
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

import com.resortmanagement.system.room.entity.HousekeepingTask;
import com.resortmanagement.system.room.service.HousekeepingTaskService;

@RestController
@RequestMapping("/api/room/housekeepingtasks")
public class HousekeepingTaskController {

    private final HousekeepingTaskService service;

    public HousekeepingTaskController(HousekeepingTaskService housekeepingTaskService) {
        this.service = housekeepingTaskService;
    }

    @GetMapping
    public ResponseEntity<List<HousekeepingTask>> getAll() {
        // TODO: add pagination and filtering params
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HousekeepingTask> getById(@PathVariable Long id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HousekeepingTask> create(@RequestBody HousekeepingTask entity) {
        // TODO: add validation
        return ResponseEntity.ok(this.service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HousekeepingTask> update(@PathVariable Long id, @RequestBody HousekeepingTask entity) {
        // TODO: implement update logic
        return ResponseEntity.ok(this.service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
