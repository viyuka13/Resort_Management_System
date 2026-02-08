package com.resortmanagement.system.billing.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.billing.entity.Payment;
import com.resortmanagement.system.billing.repository.PaymentRepository;
import com.resortmanagement.system.common.enums.PaymentStatus;
import com.resortmanagement.system.common.exception.ApplicationException;

/**
 * PaymentService
 * Purpose:
 *  - Service layer for Payment entity operations
 *  - Handles payment processing with idempotency and validation
 * Business Logic:
 *  - processPayment: Transitions payment from PENDING to SUCCESS/FAILED
 *  - Ensures idempotency using transactionRef
 *  - Validates payment before processing
 */
@Service
@Transactional
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Payment> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Payment> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByInvoiceId(UUID invoiceId) {
        return repository.findByInvoiceId(invoiceId);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByGuestId(UUID guestId) {
        return repository.findByGuestId(guestId);
    }

    @Transactional(readOnly = true)
    public Optional<Payment> findByTransactionRef(String transactionRef) {
        return repository.findByTransactionRef(transactionRef);
    }

    public Payment save(Payment payment) {
        // Validation: ensure required fields are present
        if (payment.getInvoiceId() == null) {
            throw new ApplicationException("Invoice ID is required for payment");
        }
        if (payment.getGuestId() == null) {
            throw new ApplicationException("Guest ID is required for payment");
        }
        if (payment.getAmount() == null || payment.getAmount().signum() <= 0) {
            throw new ApplicationException("Payment amount must be greater than zero");
        }
        if (payment.getPaymentMethod() == null) {
            throw new ApplicationException("Payment method is required");
        }
        
        // Check for duplicate transactionRef for idempotency
        if (payment.getTransactionRef() != null) {
            Optional<Payment> existing = repository.findByTransactionRef(payment.getTransactionRef());
            if (existing.isPresent() && !existing.get().getId().equals(payment.getId())) {
                throw new ApplicationException("Payment with transaction reference already exists: " 
                        + payment.getTransactionRef());
            }
        }
        
        return repository.save(payment);
    }

    public Payment processPayment(UUID paymentId, boolean success, String providerResponse) {
        Payment payment = repository.findById(paymentId)
                .orElseThrow(() -> new ApplicationException("Payment not found with id: " + paymentId));
        
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new ApplicationException("Only PENDING payments can be processed");
        }
        
        payment.setStatus(success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
        payment.setProcessedAt(Instant.now());
        payment.setProviderResponse(providerResponse);
        
        return repository.save(payment);
    }

    public void deleteById(UUID id) {
        // Note: Payment records should typically not be deleted for audit purposes
        // Consider throwing an exception or implementing soft-delete instead
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new ApplicationException("Payment not found with id: " + id));
        
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            throw new ApplicationException("Successful payments cannot be deleted");
        }
        
        repository.deleteById(id);
    }
}
