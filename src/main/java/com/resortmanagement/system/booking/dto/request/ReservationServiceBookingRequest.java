package com.resortmanagement.system.booking.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import com.resortmanagement.system.common.enums.ServiceBookingStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationServiceBookingRequest{
    private UUID reservationId;
    private UUID serviceItemId;
    private LocalDateTime scheduledAt;
    private Integer quantity;
    private Double price;
    private ServiceBookingStatus status; // SCHEDULED, COMPLETED, CANCELLED
    private Boolean isIncludedInPackage;
}
