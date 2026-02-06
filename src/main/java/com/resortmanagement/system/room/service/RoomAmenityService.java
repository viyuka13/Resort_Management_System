package com.resortmanagement.system.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.room.entity.RoomAmenity;
import com.resortmanagement.system.room.repository.RoomAmenityRepository;

@Service
public class RoomAmenityService {

    private final RoomAmenityRepository repository;

    public RoomAmenityService(RoomAmenityRepository repository) {
        this.repository = repository;
    }

    public List<RoomAmenity> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<RoomAmenity> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public RoomAmenity save(RoomAmenity entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
