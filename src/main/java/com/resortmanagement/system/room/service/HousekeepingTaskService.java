package com.resortmanagement.system.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.room.entity.HousekeepingTask;
import com.resortmanagement.system.room.repository.HousekeepingTaskRepository;

@Service
public class HousekeepingTaskService {

    private final HousekeepingTaskRepository repository;

    public HousekeepingTaskService(HousekeepingTaskRepository repository) {
        this.repository = repository;
    }

    public List<HousekeepingTask> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<HousekeepingTask> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public HousekeepingTask save(HousekeepingTask entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
