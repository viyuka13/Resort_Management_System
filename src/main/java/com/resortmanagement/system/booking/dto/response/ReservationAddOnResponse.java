package com.resortmanagement.system.booking.dto.response;

import java.util.UUID;

import com.resortmanagement.system.common.enums.AddOnStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationAddOnResponse{
    private UUID id;
    private String addOnCode;
    private String addOnName;
    private Integer quantity;
    private Double totalPrice;
    private Double unitPrice;
    private AddOnStatus status;
}
