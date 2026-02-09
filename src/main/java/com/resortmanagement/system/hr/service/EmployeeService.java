package com.resortmanagement.system.hr.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.hr.dto.EmployeeDTO;
import com.resortmanagement.system.hr.dto.HRMapper;
import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository repository;
    private final HRMapper mapper;

    public EmployeeService(EmployeeRepository repository, HRMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<EmployeeDTO> findAll(
            org.springframework.data.domain.Pageable pageable) {
        return repository.findByDeletedFalse(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> findById(UUID id) {
        return repository.findByIdAndDeletedFalse(id).map(mapper::toDTO);
    }

    public EmployeeDTO save(EmployeeDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        // Check for duplicate email if creating new
        if (dto.getId() == null && repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        Employee entity = mapper.toEntity(dto);
        // Set default values or handle sensitive fields here if needed in future
        if (entity.getCredentialsHash() == null) {
            entity.setCredentialsHash("TEMP_HASH"); // Placeholder as DTO doesn't carry it
        }

        return mapper.toDTO(repository.save(entity));
    }

    public EmployeeDTO update(UUID id, EmployeeDTO dto) {
        return repository.findByIdAndDeletedFalse(id)
                .map(existing -> {
                    mapper.updateEntityFromDTO(existing, dto);
                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Employee not found with id " + id);
        }
        repository.softDeleteById(id, Instant.now());
    }

    public List<EmployeeDTO> findAvailableEmployees(Instant startTime, Instant endTime) {
        // Placeholder for complex availability logic (checking shifts, leaves, etc.)
        return repository.findByDeletedFalse().stream()
                .filter(e -> e.getStatus() == Employee.EmployeeStatus.ACTIVE)
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
