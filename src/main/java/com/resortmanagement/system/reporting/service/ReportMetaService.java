package com.resortmanagement.system.reporting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.reporting.entity.ReportMeta;
import com.resortmanagement.system.reporting.repository.ReportMetaRepository;

@Service
public class ReportMetaService {

    private final ReportMetaRepository repository;

    public ReportMetaService(ReportMetaRepository repository) {
        this.repository = repository;
    }

    public List<ReportMeta> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<ReportMeta> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public ReportMeta save(ReportMeta entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
