package com.resortmanagement.system.hr.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.hr.dto.HRMapper;
import com.resortmanagement.system.hr.dto.PayrollDTO;
import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.entity.Payroll;
import com.resortmanagement.system.hr.repository.EmployeeRepository;
import com.resortmanagement.system.hr.repository.PayrollRepository;

@Service
@Transactional
public class PayrollService {

    private final PayrollRepository repository;
    private final EmployeeRepository employeeRepository;
    private final HRMapper mapper;

    public PayrollService(
            PayrollRepository repository,
            EmployeeRepository employeeRepository,
            HRMapper mapper) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<PayrollDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<PayrollDTO> findById(UUID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public PayrollDTO update(UUID id, PayrollDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    if (dto.getEmployeeId() != null && !existing.getEmployee().getId().equals(dto.getEmployeeId())) {
                        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
                        existing.setEmployee(employee);
                    }

                    existing.setPeriodStart(dto.getPeriodStart());
                    existing.setPeriodEnd(dto.getPeriodEnd());
                    existing.setGrossPay(dto.getGrossPay());
                    existing.setDeductions(dto.getDeductions());
                    // Auto-calculate or update net pay
                    if (dto.getNetPay() != null) {
                        existing.setNetPay(dto.getNetPay());
                    } else {
                        existing.setNetPay(dto.getGrossPay().subtract(dto.getDeductions()));
                    }
                    existing.setPaidAt(dto.getPaidAt());

                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Payroll not found with id " + id));
    }

    public PayrollDTO save(PayrollDTO dto) {
        if (dto.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee is required for payroll");
        }
        if (dto.getPeriodStart().isAfter(dto.getPeriodEnd())) {
            throw new IllegalArgumentException("Period start cannot be after period end");
        }

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        Payroll entity = mapper.toEntity(dto, employee);

        // Auto-calculate net pay if not set
        if (entity.getNetPay() == null) {
            entity.setNetPay(entity.getGrossPay().subtract(entity.getDeductions()));
        }
        return mapper.toDTO(repository.save(entity));
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Payroll record not found with id " + id);
        }
        repository.deleteById(id);
    }

    public List<PayrollDTO> findByEmployee(UUID employeeId) {
        return repository.findByEmployeeId(employeeId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
