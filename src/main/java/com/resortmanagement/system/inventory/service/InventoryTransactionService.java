package com.resortmanagement.system.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.inventory.entity.InventoryTransaction;
import com.resortmanagement.system.inventory.repository.InventoryTransactionRepository;

@Service
public class InventoryTransactionService {

    private final InventoryTransactionRepository repository;

    public InventoryTransactionService(InventoryTransactionRepository repository) {
        this.repository = repository;
    }

    public List<InventoryTransaction> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<InventoryTransaction> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public InventoryTransaction save(InventoryTransaction entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
