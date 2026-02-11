package com.resortmanagement.system.pricing.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RatePlanSummaryResponse {

    private UUID id;
    private String name;
    private Double basePrice;
}
