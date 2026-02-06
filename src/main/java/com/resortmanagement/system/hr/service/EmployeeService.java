package com.resortmanagement.system.hr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public Employee save(Employee entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
