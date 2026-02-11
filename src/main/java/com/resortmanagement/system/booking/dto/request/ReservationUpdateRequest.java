package com.resortmanagement.system.booking.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.resortmanagement.system.common.enums.ReservationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationUpdateRequest{
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numGuests;
    private ReservationStatus status;
    private List<BookingGuestRequest> guests;
    private List<AddOnSelectionRequest> addOns;
    private List<ReservationServiceBookingRequest> services;
}
