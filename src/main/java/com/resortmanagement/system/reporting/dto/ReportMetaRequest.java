package com.resortmanagement.system.reporting.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReportMetaRequest {
    @NotBlank
    private String name;
    private String scheduleString;
}
