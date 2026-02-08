package com.resortmanagement.system.billing.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.billing.entity.Folio;
import com.resortmanagement.system.billing.entity.FolioStatus;

/**
 * FolioRepository
 * Purpose:
 *  - Repository for Folio entity operations
 * Methods:
 *  - findByReservationId: Find all folios for a specific reservation
 *  - findByStatus: Find folios by their status (OPEN/CLOSED)
 *  - findByReservationIdAndName: Find specific folio by reservation and name
 */
@Repository
public interface FolioRepository extends JpaRepository<Folio, UUID> {
    
    List<Folio> findByReservationId(UUID reservationId);
    
    List<Folio> findByStatus(FolioStatus status);
    
    Optional<Folio> findByReservationIdAndName(UUID reservationId, String name);
}
