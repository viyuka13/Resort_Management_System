package com.resortmanagement.system.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.booking.entity.ReservationDailyRate;
import com.resortmanagement.system.booking.repository.ReservationDailyRateRepository;

@Service
public class ReservationDailyRateService {

    private final ReservationDailyRateRepository repository;

    public ReservationDailyRateService(ReservationDailyRateRepository repository) {
        this.repository = repository;
    }

    public List<ReservationDailyRate> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<ReservationDailyRate> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public ReservationDailyRate save(ReservationDailyRate entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
