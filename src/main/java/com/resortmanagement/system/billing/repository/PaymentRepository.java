package com.resortmanagement.system.billing.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.billing.entity.Payment;
import com.resortmanagement.system.common.enums.PaymentStatus;

/**
 * PaymentRepository
 * Purpose:
 *  - Repository for Payment entity operations
 * Methods:
 *  - findByInvoiceId: Find all payments for a specific invoice
 *  - findByGuestId: Find all payments made by a specific guest
 *  - findByTransactionRef: Find payment by PSP transaction reference (for idempotency)
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    
    List<Payment> findByInvoiceId(UUID invoiceId);
    
    List<Payment> findByGuestId(UUID guestId);
    
    Optional<Payment> findByTransactionRef(String transactionRef);
    
    List<Payment> findByStatus(PaymentStatus status);
}
