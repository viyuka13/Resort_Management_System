package com.resortmanagement.system.billing.entity;

/**
 * InvoiceStatus enum
 * Purpose: Represents the status of an invoice
 * Values:
 *  - DRAFT: Invoice is being prepared
 *  - ISSUED: Invoice has been issued to guest
 *  - PAID: Invoice has been fully paid
 *  - PARTIALLY_PAID: Invoice has been partially paid
 *  - REFUNDED: Invoice has been refunded
 *  - CANCELLED: Invoice has been cancelled
 */
public enum InvoiceStatus {
    DRAFT,
    ISSUED,
    PAID,
    PARTIALLY_PAID,
    REFUNDED,
    CANCELLED
}
