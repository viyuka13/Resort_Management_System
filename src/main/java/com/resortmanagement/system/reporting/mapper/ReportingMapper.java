package com.resortmanagement.system.reporting.mapper;

import com.resortmanagement.system.reporting.dto.AuditLogResponse;
import com.resortmanagement.system.reporting.dto.ReportMetaRequest;
import com.resortmanagement.system.reporting.dto.ReportMetaResponse;
import com.resortmanagement.system.reporting.entity.AuditLog;
import com.resortmanagement.system.reporting.entity.ReportMeta;

public class ReportingMapper {

    public static ReportMeta toEntity(ReportMetaRequest request) {
        ReportMeta meta = new ReportMeta();
        meta.setName(request.getName());
        meta.setScheduleString(request.getScheduleString());
        return meta;
    }

    public static ReportMetaResponse toResponse(ReportMeta entity) {
        ReportMetaResponse response = new ReportMetaResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setScheduleString(entity.getScheduleString());
        response.setLastRunAt(entity.getLastRunAt());
        response.setOwnerId(entity.getOwnerId());
        return response;
    }

    public static AuditLogResponse toResponse(AuditLog entity) {
        AuditLogResponse response = new AuditLogResponse();
        response.setId(entity.getId());
        response.setTargetEntity(entity.getTargetEntity());
        response.setTargetId(entity.getTargetId());
        response.setAction(entity.getAction());
        response.setPerformedBy(entity.getPerformedBy());
        response.setTimestamp(entity.getTimestamp());
        response.setChangesJson(entity.getChangesJson());
        response.setReason(entity.getReason());
        return response;
    }
}
