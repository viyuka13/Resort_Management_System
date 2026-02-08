package com.resortmanagement.system.billing.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.billing.entity.Refund;
import com.resortmanagement.system.billing.entity.RefundStatus;

/**
 * RefundRepository
 * Purpose:
 *  - Repository for Refund entity operations
 * Methods:
 *  - findByPaymentId: Find all refunds for a specific payment
 *  - findByStatus: Find refunds by their processing status
 */
@Repository
public interface RefundRepository extends JpaRepository<Refund, UUID> {
    
    List<Refund> findByPaymentId(UUID paymentId);
    
    List<Refund> findByStatus(RefundStatus status);
}
