package com.resortmanagement.system.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.hr.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // TODO: add custom queries if needed
}
