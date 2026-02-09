package com.resortmanagement.system.hr.dto;

import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.Collections;

import com.resortmanagement.system.hr.entity.Employee;
import com.resortmanagement.system.hr.entity.Role;
import com.resortmanagement.system.hr.entity.EmployeeRole;
import com.resortmanagement.system.hr.entity.ShiftSchedule;
import com.resortmanagement.system.hr.entity.Payroll;

@Component
public class HRMapper {

    // Employee
    public EmployeeDTO toDTO(Employee entity) {
        if (entity == null)
            return null;
        return EmployeeDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .hireDate(entity.getHireDate())
                .status(entity.getStatus())
                .roles(entity.getRoles() != null
                        ? entity.getRoles().stream().map(this::toDTO).collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null)
            return null;
        return Employee.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .hireDate(dto.getHireDate())
                .status(dto.getStatus())
                // Roles usually handled separately via service
                .build();
    }

    public void updateEntityFromDTO(Employee entity, EmployeeDTO dto) {
        if (dto == null || entity == null)
            return;
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setHireDate(dto.getHireDate());
        entity.setStatus(dto.getStatus());
    }

    // Role
    public RoleDTO toDTO(Role entity) {
        if (entity == null)
            return null;
        return RoleDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .permissions(entity.getPermissionsJson())
                .build();
    }

    public Role toEntity(RoleDTO dto) {
        if (dto == null)
            return null;
        return Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .permissionsJson(dto.getPermissions())
                .build();
    }

    // EmployeeRole
    public EmployeeRoleDTO toDTO(EmployeeRole entity) {
        if (entity == null)
            return null;
        return EmployeeRoleDTO.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployee() != null ? entity.getEmployee().getId() : null)
                .employeeName(entity.getEmployee() != null
                        ? entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName()
                        : null)
                .roleId(entity.getRole() != null ? entity.getRole().getId() : null)
                .roleName(entity.getRole() != null ? entity.getRole().getName() : null)
                .assignedDate(entity.getAssignedDate())
                .endDate(entity.getEndDate())
                .build();
    }

    // ShiftSchedule
    public ShiftScheduleDTO toDTO(ShiftSchedule entity) {
        if (entity == null)
            return null;
        return ShiftScheduleDTO.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployee() != null ? entity.getEmployee().getId() : null)
                .employeeName(entity.getEmployee() != null
                        ? entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName()
                        : null)
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .position(entity.getPosition())
                .location(entity.getLocation())
                .build();
    }

    public ShiftSchedule toEntity(ShiftScheduleDTO dto, Employee employee) {
        if (dto == null)
            return null;
        return ShiftSchedule.builder()
                .id(dto.getId())
                .employee(employee)
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .position(dto.getPosition())
                .location(dto.getLocation())
                .build();
    }

    // Payroll
    public PayrollDTO toDTO(Payroll entity) {
        if (entity == null)
            return null;
        return PayrollDTO.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployee() != null ? entity.getEmployee().getId() : null)
                .employeeName(entity.getEmployee() != null
                        ? entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName()
                        : null)
                .periodStart(entity.getPeriodStart())
                .periodEnd(entity.getPeriodEnd())
                .grossPay(entity.getGrossPay())
                .deductions(entity.getDeductions())
                .netPay(entity.getNetPay())
                .paidAt(entity.getPaidAt())
                .build();
    }

    public Payroll toEntity(PayrollDTO dto, Employee employee) {
        if (dto == null)
            return null;
        return Payroll.builder()
                .id(dto.getId())
                .employee(employee)
                .periodStart(dto.getPeriodStart())
                .periodEnd(dto.getPeriodEnd())
                .grossPay(dto.getGrossPay())
                .deductions(dto.getDeductions())
                .netPay(dto.getNetPay())
                .paidAt(dto.getPaidAt())
                .build();
    }
}
