package com.resortmanagement.system.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.booking.entity.ReservationServiceBooking;
import com.resortmanagement.system.booking.repository.ReservationServiceBookingRepository;

@Service
public class ReservationServiceBookingService {

    private final ReservationServiceBookingRepository repository;

    public ReservationServiceBookingService(ReservationServiceBookingRepository repository) {
        this.repository = repository;
    }

    public List<ReservationServiceBooking> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<ReservationServiceBooking> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public ReservationServiceBooking save(ReservationServiceBooking entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
