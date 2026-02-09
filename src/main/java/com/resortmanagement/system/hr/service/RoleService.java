package com.resortmanagement.system.hr.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.hr.dto.HRMapper;
import com.resortmanagement.system.hr.dto.RoleDTO;
import com.resortmanagement.system.hr.entity.Role;
import com.resortmanagement.system.hr.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

    private final RoleRepository repository;
    private final HRMapper mapper;

    public RoleService(RoleRepository repository, HRMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<RoleDTO> findAll(org.springframework.data.domain.Pageable pageable) {
        return repository.findByDeletedFalse(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<RoleDTO> findById(UUID id) {
        return repository.findByIdAndDeletedFalse(id).map(mapper::toDTO);
    }

    public RoleDTO save(RoleDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Role name is required");
        }
        if (dto.getId() == null && repository.findByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("Role name already exists");
        }
        Role entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public RoleDTO update(UUID id, RoleDTO dto) {
        return repository.findByIdAndDeletedFalse(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    existing.setPermissionsJson(dto.getPermissions());
                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    public void deleteById(UUID id) {
        repository.softDeleteById(id, Instant.now());
    }
}
