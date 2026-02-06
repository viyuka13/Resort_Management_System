package com.resortmanagement.system.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.room.entity.MaintenanceRequest;
import com.resortmanagement.system.room.repository.MaintenanceRequestRepository;

@Service
public class MaintenanceRequestService {

    private final MaintenanceRequestRepository repository;

    public MaintenanceRequestService(MaintenanceRequestRepository repository) {
        this.repository = repository;
    }

    public List<MaintenanceRequest> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<MaintenanceRequest> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public MaintenanceRequest save(MaintenanceRequest entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
