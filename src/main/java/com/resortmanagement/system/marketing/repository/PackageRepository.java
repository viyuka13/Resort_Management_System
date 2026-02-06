package com.resortmanagement.system.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.marketing.entity.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    // TODO: add custom queries if needed
}
