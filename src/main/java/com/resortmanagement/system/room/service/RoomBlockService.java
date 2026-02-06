package com.resortmanagement.system.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.room.entity.RoomBlock;
import com.resortmanagement.system.room.repository.RoomBlockRepository;

@Service
public class RoomBlockService {

    private final RoomBlockRepository repository;

    public RoomBlockService(RoomBlockRepository repository) {
        this.repository = repository;
    }

    public List<RoomBlock> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<RoomBlock> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public RoomBlock save(RoomBlock entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
