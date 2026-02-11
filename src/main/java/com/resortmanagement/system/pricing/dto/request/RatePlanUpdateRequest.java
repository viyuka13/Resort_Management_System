package com.resortmanagement.system.pricing.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatePlanUpdateRequest {

    private String description;
    private Double basePrice;
    private Boolean refundable;
    private Integer minStayNights;
    private Integer maxStayNights;
}
