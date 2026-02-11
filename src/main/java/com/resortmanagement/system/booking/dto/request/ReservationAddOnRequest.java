package com.resortmanagement.system.booking.dto.request;


import com.resortmanagement.system.common.enums.AddOnStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationAddOnRequest{
    private String addOnCode;
    private String addOnName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private AddOnStatus status;
}
