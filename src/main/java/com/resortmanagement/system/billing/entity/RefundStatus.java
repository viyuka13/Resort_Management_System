package com.resortmanagement.system.billing.entity;

/**
 * RefundStatus enum
 * Purpose: Represents the status of a refund operation
 * Values:
 *  - REQUESTED: Refund has been requested
 *  - PROCESSING: Refund is being processed
 *  - SUCCESS: Refund completed successfully
 *  - FAILED: Refund failed
 */
public enum RefundStatus {
    REQUESTED,
    PROCESSING,
    SUCCESS,
    FAILED
}
