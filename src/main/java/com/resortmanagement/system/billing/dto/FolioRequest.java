package com.resortmanagement.system.billing.dto;

import java.util.UUID;

import com.resortmanagement.system.billing.entity.FolioStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FolioRequest {
    private UUID reservationId;
    @NotBlank
    private String name;
    private UUID bookingGuestId;
    private FolioStatus status;
}
