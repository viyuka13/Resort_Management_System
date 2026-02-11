package com.resortmanagement.system.pricing.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingQuoteRequest {

    @Valid
    @NotEmpty
    private List<GuestPricingInput> guestInputs;
}
