package com.resortmanagement.system.billing.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.billing.entity.Invoice;
import com.resortmanagement.system.billing.entity.InvoiceStatus;

/**
 * InvoiceRepository
 * Purpose:
 *  - Repository for Invoice entity operations
 * Methods:
 *  - findByGuestId: Find all invoices for a specific guest
 *  - findByStatus: Find invoices by their status
 *  - findByFolioId: Find invoice associated with a specific folio
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    
    List<Invoice> findByGuestId(UUID guestId);
    
    List<Invoice> findByStatus(InvoiceStatus status);
    
    Optional<Invoice> findByFolioId(UUID folioId);
}
