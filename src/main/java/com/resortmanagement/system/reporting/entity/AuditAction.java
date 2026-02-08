package com.resortmanagement.system.reporting.entity;

/**
 * AuditAction enum
 * Purpose: Represents the type of audit action performed
 * Values:
 *  - CREATE: Entity was created
 *  - UPDATE: Entity was updated
 *  - DELETE: Entity was deleted
 */
public enum AuditAction {
    CREATE,
    UPDATE,
    DELETE
}
