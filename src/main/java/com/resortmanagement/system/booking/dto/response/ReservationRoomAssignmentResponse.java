package com.resortmanagement.system.booking.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import com.resortmanagement.system.common.enums.RoomAssignmentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRoomAssignmentResponse{
    private UUID id;
    private UUID roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private RoomAssignmentStatus status;
}