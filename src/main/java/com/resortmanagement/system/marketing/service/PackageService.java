package com.resortmanagement.system.marketing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.marketing.entity.Package;
import com.resortmanagement.system.marketing.repository.PackageRepository;

@Service
public class PackageService {

    private final PackageRepository repository;

    public PackageService(PackageRepository repository) {
        this.repository = repository;
    }

    public List<Package> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<Package> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public Package save(Package entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
