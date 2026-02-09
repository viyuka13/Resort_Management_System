package com.resortmanagement.system.hr.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftScheduleDTO {
    private UUID id;
    private UUID employeeId;
    private String employeeName;
    private Instant startTime;
    private Instant endTime;
    private String position;
    private String location;
}
