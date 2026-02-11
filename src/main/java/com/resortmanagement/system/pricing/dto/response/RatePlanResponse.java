package com.resortmanagement.system.pricing.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RatePlanResponse {

    private UUID id;
    private String name;
    private String description;
    private Double basePrice;
    private Boolean refundable;
    private Integer minStayNights;
    private Integer maxStayNights;
}

