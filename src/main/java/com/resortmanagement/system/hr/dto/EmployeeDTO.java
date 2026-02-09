package com.resortmanagement.system.hr.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.resortmanagement.system.hr.entity.Employee.EmployeeStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate hireDate;
    private EmployeeStatus status;
    private List<EmployeeRoleDTO> roles;
    // We intentionally exclude credentialsHash and sensitive internal details
}
