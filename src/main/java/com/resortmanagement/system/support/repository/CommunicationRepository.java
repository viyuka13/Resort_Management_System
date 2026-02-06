package com.resortmanagement.system.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.support.entity.Communication;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication, Long> {
    // TODO: add custom queries if needed
}
