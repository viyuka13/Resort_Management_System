package com.resortmanagement.system.booking.dto.response;

import java.util.UUID;

import com.resortmanagement.system.common.enums.GuestType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingGuestResponse{
    private UUID id;
    private UUID guestId;
    private UUID reservationId;
    private GuestType guestType;
    private Integer age;
    private Boolean isPrimary;
    private String specialNeeds;
}
