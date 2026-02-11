package com.resortmanagement.system.pricing.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.pricing.dto.request.GuestPricingInput;
import com.resortmanagement.system.pricing.dto.request.PricingQuoteRequest;
import com.resortmanagement.system.pricing.dto.response.GuestPriceBreakdown;
import com.resortmanagement.system.pricing.dto.response.PriceBreakdownPerDay;
import com.resortmanagement.system.pricing.dto.response.PricingQuoteResponse;
import com.resortmanagement.system.pricing.entity.RateHistory;
import com.resortmanagement.system.pricing.entity.RatePlan;
import com.resortmanagement.system.pricing.repository.RateHistoryRepository;
import com.resortmanagement.system.pricing.repository.RatePlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PricingQuoteService {

    private final RatePlanRepository ratePlanRepository;
    private final RateHistoryRepository rateHistoryRepository;

    public PricingQuoteResponse calculate(PricingQuoteRequest request) {

        List<GuestPriceBreakdown> breakdowns = new ArrayList<>();
        double grandTotal = 0;

        for (GuestPricingInput input : request.getGuestInputs()) {

            RatePlan plan = ratePlanRepository.findById(input.getRatePlanId())
                    .orElseThrow(() -> new RuntimeException("RatePlan not found"));

            LocalDate current = input.getCheckIn();
            List<PriceBreakdownPerDay> daily = new ArrayList<>();
            double total = 0;

            while (current.isBefore(input.getCheckOut())) {

                double price = rateHistoryRepository
                        .findApplicableRate(plan.getId(), current)
                        .map(RateHistory::getPrice)
                        .orElse(plan.getBasePrice());

                daily.add(PriceBreakdownPerDay.builder()
                        .date(current)
                        .basePrice(plan.getBasePrice())
                        .finalPrice(price)
                        .build());

                total += price;
                current = current.plusDays(1);
            }

            grandTotal += total;

            breakdowns.add(GuestPriceBreakdown.builder()
                    .ratePlanId(plan.getId())
                    .dailyBreakdown(daily)
                    .totalAmount(total)
                    .build());
        }

        return PricingQuoteResponse.builder()
                .guestBreakdowns(breakdowns)
                .grandTotal(grandTotal)
                .build();
    }
}
