package com.resortmanagement.system.booking.dto.request;

import java.time.LocalDate;

import com.resortmanagement.system.common.enums.RoomAssignmentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRoomAssignmentRequest{
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private RoomAssignmentStatus status;
}
