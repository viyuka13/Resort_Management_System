/*
 * ReportMeta.java
 * Purpose:
 *  - Metadata about scheduled reports.
 * Fields:
 *  - id UUID
 *  - name String
 *  - scheduleString (cron)
 *  - lastRunAt Instant
 *  - ownerId UUID
 * Notes:
 *  - Do not store report data here; use separate storage or exports.
 * File: reporting/entity/ReportMeta.java
 */
package com.resortmanagement.system.reporting.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "report_meta")
@Getter
@Setter
public class ReportMeta extends AuditableSoftDeletable {

    @Id
    @UuidGenerator
    @Column(name = "report_id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "schedule_string", length = 100)
    private String scheduleString;

    @Column(name = "last_run_at")
    private Instant lastRunAt;

    @Column(name = "owner_id", columnDefinition = "VARCHAR(36)")
    private UUID ownerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportMeta)) return false;
        ReportMeta that = (ReportMeta) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}