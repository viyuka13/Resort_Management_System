package com.resortmanagement.system.reporting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.reporting.entity.AuditLog;
import com.resortmanagement.system.reporting.repository.AuditLogRepository;

@Service
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public List<AuditLog> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<AuditLog> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public AuditLog save(AuditLog entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
