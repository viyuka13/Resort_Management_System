package com.resortmanagement.system.hr.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.hr.dto.HRMapper;
import com.resortmanagement.system.hr.dto.ShiftScheduleDTO;
import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.entity.ShiftSchedule;
import com.resortmanagement.system.hr.repository.EmployeeRepository;
import com.resortmanagement.system.hr.repository.ShiftScheduleRepository;

@Service
@Transactional
public class ShiftScheduleService {

    private final ShiftScheduleRepository repository;
    private final EmployeeRepository employeeRepository;
    private final HRMapper mapper;

    public ShiftScheduleService(
            ShiftScheduleRepository repository,
            EmployeeRepository employeeRepository,
            HRMapper mapper) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<ShiftScheduleDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<ShiftScheduleDTO> findById(UUID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public ShiftScheduleDTO save(ShiftScheduleDTO dto) {
        if (dto.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee is required");
        }
        if (dto.getStartTime() == null || dto.getEndTime() == null) {
            throw new IllegalArgumentException("Start and End times are required");
        }
        if (dto.getStartTime().isAfter(dto.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        ShiftSchedule entity = mapper.toEntity(dto, null);

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        entity.setEmployee(employee);

        return mapper.toDTO(repository.save(entity));
    }

    public ShiftScheduleDTO update(UUID id, ShiftScheduleDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    if (dto.getEmployeeId() != null && !existing.getEmployee().getId().equals(dto.getEmployeeId())) {
                        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
                        existing.setEmployee(employee);
                    }

                    existing.setStartTime(dto.getStartTime());
                    existing.setEndTime(dto.getEndTime());
                    existing.setPosition(dto.getPosition());
                    existing.setLocation(dto.getLocation());

                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("ShiftSchedule not found with id " + id));
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ShiftSchedule not found with id " + id);
        }
        repository.deleteById(id);
    }
}
