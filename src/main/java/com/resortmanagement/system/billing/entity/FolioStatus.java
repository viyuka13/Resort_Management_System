package com.resortmanagement.system.billing.entity;

/**
 * FolioStatus enum
 * Purpose: Represents the status of a folio (billing bucket)
 * Values:
 *  - OPEN: Folio is open and charges can be added
 *  - CLOSED: Folio is closed and ready for invoicing
 */
public enum FolioStatus {
    OPEN,
    CLOSED
}
