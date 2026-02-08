package com.resortmanagement.system.common.enums;

/**
 * PaymentStatus enum
 * Purpose: Represents the status of a payment transaction
 * Values:
 *  - PENDING: Payment is pending processing
 *  - SUCCESS: Payment completed successfully
 *  - FAILED: Payment failed
 *  - REFUNDED: Payment has been refunded
 */
public enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED,
    REFUNDED
}
