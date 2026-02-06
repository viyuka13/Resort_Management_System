package com.resortmanagement.system.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.booking.entity.Reservation;
import com.resortmanagement.system.booking.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<Reservation> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public Reservation save(Reservation entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
