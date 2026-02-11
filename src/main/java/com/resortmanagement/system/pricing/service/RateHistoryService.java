package com.resortmanagement.system.pricing.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.pricing.dto.response.RateHistoryResponse;
import com.resortmanagement.system.pricing.entity.RateHistory;
import com.resortmanagement.system.pricing.entity.RatePlan;
import com.resortmanagement.system.pricing.repository.RateHistoryRepository;
import com.resortmanagement.system.pricing.repository.RatePlanRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class RateHistoryService {

    private final RateHistoryRepository repository;
    private final RatePlanRepository ratePlanRepository;

    public RateHistoryResponse create(UUID ratePlanId,
                                      Double price,
                                      LocalDate start,
                                      LocalDate end,
                                      String seasonName) {

        RatePlan plan = ratePlanRepository.findById(ratePlanId)
                .orElseThrow(() -> new RuntimeException("RatePlan not found"));

        RateHistory history = new RateHistory();
        history.setRatePlanId(plan);
        history.setPrice(price);
        history.setStartDate(start);
        history.setEndDate(end);
        history.setSeasonName(seasonName);

        return toResponse(repository.save(history));
    }

    private RateHistoryResponse toResponse(RateHistory r) {
        return RateHistoryResponse.builder()
                .id(r.getId())
                .ratePlanId(r.getRatePlanId().getId())
                .price(r.getPrice())
                .seasonName(r.getSeasonName())
                .startDate(r.getStartDate())
                .endDate(r.getEndDate())
                .build();
    }
}
