package com.resortmanagement.system.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.room.entity.Room;
import com.resortmanagement.system.room.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<Room> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public Room save(Room entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
