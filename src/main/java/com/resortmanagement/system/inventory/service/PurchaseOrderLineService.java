package com.resortmanagement.system.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.inventory.entity.PurchaseOrderLine;
import com.resortmanagement.system.inventory.repository.PurchaseOrderLineRepository;

@Service
public class PurchaseOrderLineService {

    private final PurchaseOrderLineRepository repository;

    public PurchaseOrderLineService(PurchaseOrderLineRepository repository) {
        this.repository = repository;
    }

    public List<PurchaseOrderLine> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<PurchaseOrderLine> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public PurchaseOrderLine save(PurchaseOrderLine entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
