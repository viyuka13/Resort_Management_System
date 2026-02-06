package com.resortmanagement.system.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.booking.entity.ReservationAddOn;
import com.resortmanagement.system.booking.repository.ReservationAddOnRepository;

@Service
public class ReservationAddOnService {

    private final ReservationAddOnRepository repository;

    public ReservationAddOnService(ReservationAddOnRepository repository) {
        this.repository = repository;
    }

    public List<ReservationAddOn> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<ReservationAddOn> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public ReservationAddOn save(ReservationAddOn entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
