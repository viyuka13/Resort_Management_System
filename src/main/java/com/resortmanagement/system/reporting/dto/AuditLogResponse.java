package com.resortmanagement.system.reporting.dto;

import java.time.Instant;
import java.util.UUID;

import com.resortmanagement.system.reporting.entity.AuditAction;

import lombok.Data;

@Data
public class AuditLogResponse {
    private UUID id;
    private String targetEntity;
    private UUID targetId;
    private AuditAction action;
    private String performedBy;
    private Instant timestamp;
    private String changesJson;
    private String reason;
}
