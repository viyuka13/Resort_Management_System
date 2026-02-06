package com.resortmanagement.system.marketing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.marketing.entity.Promotion;
import com.resortmanagement.system.marketing.repository.PromotionRepository;

@Service
public class PromotionService {

    private final PromotionRepository repository;

    public PromotionService(PromotionRepository repository) {
        this.repository = repository;
    }

    public List<Promotion> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<Promotion> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public Promotion save(Promotion entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
