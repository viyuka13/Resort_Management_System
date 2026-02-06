package com.resortmanagement.system.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.room.entity.RoomType;
import com.resortmanagement.system.room.repository.RoomTypeRepository;

@Service
public class RoomTypeService {

    private final RoomTypeRepository repository;

    public RoomTypeService(RoomTypeRepository repository) {
        this.repository = repository;
    }

    public List<RoomType> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<RoomType> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public RoomType save(RoomType entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
