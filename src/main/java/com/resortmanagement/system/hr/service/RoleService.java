package com.resortmanagement.system.hr.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.hr.entity.Role;
import com.resortmanagement.system.hr.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public org.springframework.data.domain.Page<Role> findAll(org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Role> findById(UUID id) {
        return repository.findById(id);
    }

    public Role save(Role entity) {
        return repository.save(entity);
    }

    public Role update(UUID id, Role entity) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(entity.getName());
                    existing.setDescription(entity.getDescription());
                    existing.setPermissions(entity.getPermissions());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Role not found with id " + id);
        }
        repository.deleteById(id);
    }
}
