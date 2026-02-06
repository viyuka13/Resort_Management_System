package com.resortmanagement.system.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.marketing.entity.PackageItem;

@Repository
public interface PackageItemRepository extends JpaRepository<PackageItem, Long> {
    // TODO: add custom queries if needed
}
