package com.resortmanagement.system.pricing.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatePlanCreateRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Double basePrice;

    private Boolean refundable;

    private Integer minStayNights;
    private Integer maxStayNights;
}
