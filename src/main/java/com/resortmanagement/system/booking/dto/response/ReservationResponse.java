package com.resortmanagement.system.booking.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import com.resortmanagement.system.common.enums.ReservationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationResponse{
    private UUID id;
    private UUID guestId;
    private UUID roomTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;
    private Integer numGuests;
}
