package com.resortmanagement.system.pricing.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RateHistoryResponse {

    private UUID id;
    private UUID ratePlanId;
    private Double price;
    private String seasonName;
    private LocalDate startDate;
    private LocalDate endDate;
}

