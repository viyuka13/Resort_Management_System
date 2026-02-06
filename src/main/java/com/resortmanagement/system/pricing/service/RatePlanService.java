package com.resortmanagement.system.pricing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.pricing.entity.RatePlan;
import com.resortmanagement.system.pricing.repository.RatePlanRepository;

@Service
public class RatePlanService {

    private final RatePlanRepository repository;

    public RatePlanService(RatePlanRepository repository) {
        this.repository = repository;
    }

    public List<RatePlan> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<RatePlan> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public RatePlan save(RatePlan entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
