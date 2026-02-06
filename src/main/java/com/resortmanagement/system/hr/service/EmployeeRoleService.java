/*
TODO: HR repositories and services guidelines
Repositories:
 - Standard JpaRepository for each entity.

Services:
 - EmployeeService: create/modify/disable employees; optionally integrate with IdP.
 - ShiftScheduleService: enforce constraints and manage shifts.
 - PayrollService: generate payroll; mark paid via accounting integration.

File: hr/repository/<File>.java, hr/service/<File>.java
*/
package com.resortmanagement.system.hr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.hr.entity.EmployeeRole;
import com.resortmanagement.system.hr.repository.EmployeeRoleRepository;

@Service
public class EmployeeRoleService {

    private final EmployeeRoleRepository repository;

    public EmployeeRoleService(EmployeeRoleRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeRole> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<EmployeeRole> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public EmployeeRole save(EmployeeRole entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
