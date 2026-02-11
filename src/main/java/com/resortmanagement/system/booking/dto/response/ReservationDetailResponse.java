package com.resortmanagement.system.booking.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.resortmanagement.system.common.enums.ReservationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDetailResponse{
    private UUID id;
    private String roomType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numGuests;
    private ReservationStatus status;
    private List<BookingGuestResponse> guests;
    private List<ReservationRoomAssignmentResponse> roomAssignments;
    private List<ReservationAddOnResponse> addOns;
    private List<ReservationDailyRateResponse> dailyRates;
    private List<ReservationServiceBookingResponse> services;
}
