package com.resortmanagement.system.booking.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDailyRateResponse{
    private UUID Id;
    private LocalDate date;
    private Boolean isPackageRate;
    private Double amount;
    private UUID ratePlanId;
}
