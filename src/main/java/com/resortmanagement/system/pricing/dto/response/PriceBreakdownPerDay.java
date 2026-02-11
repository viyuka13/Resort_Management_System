package com.resortmanagement.system.pricing.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PriceBreakdownPerDay {

    private LocalDate date;
    private Double basePrice;
    private Double finalPrice;
}

