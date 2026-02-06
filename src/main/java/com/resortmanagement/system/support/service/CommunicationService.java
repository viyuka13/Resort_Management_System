package com.resortmanagement.system.support.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.support.entity.Communication;
import com.resortmanagement.system.support.repository.CommunicationRepository;

@Service
public class CommunicationService {

    private final CommunicationRepository repository;

    public CommunicationService(CommunicationRepository repository) {
        this.repository = repository;
    }

    public List<Communication> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<Communication> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public Communication save(Communication entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
