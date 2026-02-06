package com.resortmanagement.system.fnb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.fnb.entity.Menu;
import com.resortmanagement.system.fnb.repository.MenuRepository;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public List<Menu> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<Menu> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public Menu save(Menu entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
