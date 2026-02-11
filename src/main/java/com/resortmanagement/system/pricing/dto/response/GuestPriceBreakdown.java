package com.resortmanagement.system.pricing.dto.response;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GuestPriceBreakdown {

    private UUID ratePlanId;
    private List<PriceBreakdownPerDay> dailyBreakdown;
    private Double totalAmount;
}

