package com.resortmanagement.system.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.booking.entity.BookingGuest;
import com.resortmanagement.system.booking.repository.BookingGuestRepository;

@Service
public class BookingGuestService {

    private final BookingGuestRepository repository;

    public BookingGuestService(BookingGuestRepository repository) {
        this.repository = repository;
    }

    public List<BookingGuest> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<BookingGuest> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public BookingGuest save(BookingGuest entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
