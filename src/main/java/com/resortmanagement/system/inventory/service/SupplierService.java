package com.resortmanagement.system.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.inventory.entity.Supplier;
import com.resortmanagement.system.inventory.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    public List<Supplier> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<Supplier> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public Supplier save(Supplier entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
