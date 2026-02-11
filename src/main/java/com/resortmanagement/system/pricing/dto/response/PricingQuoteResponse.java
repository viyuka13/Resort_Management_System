package com.resortmanagement.system.pricing.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PricingQuoteResponse {

    private List<GuestPriceBreakdown> guestBreakdowns;
    private Double grandTotal;
}
