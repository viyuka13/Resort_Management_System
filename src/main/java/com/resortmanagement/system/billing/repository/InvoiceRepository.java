/**
 * TODO: File-level design notes
 *
 * WHAT: Repository interface for Invoice persistence.
 * WHY: Keep persistence-specific queries and projections here; small, focused custom queries are acceptable.
 * HOW:
 *  - Keep complex queries as well-tested repository methods or projection interfaces.
 *  - Avoid embedding business rules here; return domain entities or DTO projections.
 * Data owned: Invoice entity and any query projections.
 * Relationships: Invoice -> Folio/Payment; use joins carefully and test performance.
 * Security: Repository layer is not responsible for access control; validate in services/controllers.
 * Audit: Use database auditing or auditing annotations on entities.
 * Forbidden responsibilities: No business orchestration, no external side-effects.
 *
 * Tests: Add repository tests and integration tests for query behavior.
 */
package com.resortmanagement.system.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.billing.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // TODO: add custom queries if needed
}
