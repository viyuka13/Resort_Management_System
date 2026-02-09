package com.resortmanagement.system.hr.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.resortmanagement.system.hr.dto.ShiftScheduleDTO;
import com.resortmanagement.system.hr.service.ShiftScheduleService;

@RestController
@RequestMapping("/api/hr/shift_schedules")
public class ShiftScheduleController {

    private final ShiftScheduleService service;

    public ShiftScheduleController(ShiftScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ShiftScheduleDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.service.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftScheduleDTO> getById(@PathVariable UUID id) {
        return this.service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShiftScheduleDTO> create(@RequestBody ShiftScheduleDTO dto) {
        if (dto.getEmployeeId() == null || dto.getStartTime() == null || dto.getEndTime() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShiftScheduleDTO> update(@PathVariable UUID id, @RequestBody ShiftScheduleDTO dto) {
        return ResponseEntity.ok(this.service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
