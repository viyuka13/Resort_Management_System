/*
 * AuditLog.java
 * Purpose:
 *  - Stores detailed history of changes (what changed, from->to, reason).
 * Fields:
 *  - id UUID
 *  - targetEntity String
 *  - targetId UUID
 *  - action String (CREATE/UPDATE/DELETE)
 *  - performedBy String
 *  - timestamp Instant
 *  - changesJson String (old/new values)
 *  - reason String (optional)
 * Notes:
 *  - Write entries in service layer where complex business changes occur.
 *  - Optionally create DB triggers for guaranteed capture of table-level changes but service-level logging gives richer context (reason).
 * File: reporting/entity/AuditLog.java
 */
package com.resortmanagement.system.reporting.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "audit_log")
@Getter
@Setter
public class AuditLog {

    @Id
    @UuidGenerator
    @Column(name = "audit_id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "target_entity", nullable = false, length = 100)
    private String targetEntity;

    @NotNull
    @Column(name = "target_id", columnDefinition = "VARCHAR(36)", nullable = false)
    private UUID targetId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false, length = 20)
    private AuditAction action;

    @NotBlank
    @Column(name = "performed_by", nullable = false, length = 100)
    private String performedBy;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private Instant timestamp = Instant.now();

    @Column(name = "changes_json", columnDefinition = "TEXT")
    private String changesJson;

    @Column(name = "reason", length = 500)
    private String reason;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditLog)) return false;
        AuditLog auditLog = (AuditLog) o;
        return id != null && id.equals(auditLog.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}