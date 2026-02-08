package com.resortmanagement.system.billing.entity;

/**
 * PaymentMethod enum
 * Purpose: Represents the payment method used for a transaction
 * Values:
 *  - CARD: Credit/Debit card payment
 *  - UPI: Unified Payments Interface
 *  - CASH: Cash payment
 *  - WALLET: Digital wallet payment
 *  - BANK_TRANSFER: Direct bank transfer
 */
public enum PaymentMethod {
    CARD,
    UPI,
    CASH,
    WALLET,
    BANK_TRANSFER
}
