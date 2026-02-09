package com.resortmanagement.system.marketing.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.marketing.dto.MarketingMapper;
import com.resortmanagement.system.marketing.dto.PromotionDTO;
import com.resortmanagement.system.marketing.entity.Promotion;
import com.resortmanagement.system.marketing.repository.PromotionRepository;
import com.resortmanagement.system.marketing.strategy.PromotionStrategy;
import com.resortmanagement.system.marketing.strategy.PromotionStrategyFactory;

@Service
@Transactional
public class PromotionService {

    private final PromotionRepository repository;
    private final PromotionStrategyFactory strategyFactory;
    private final MarketingMapper mapper;

    public PromotionService(PromotionRepository repository, PromotionStrategyFactory strategyFactory,
            MarketingMapper mapper) {
        this.repository = repository;
        this.strategyFactory = strategyFactory;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<PromotionDTO> findAll(
            org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<PromotionDTO> findById(UUID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public PromotionDTO update(UUID id, PromotionDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setCode(dto.getCode());
                    existing.setDescription(dto.getDescription());
                    existing.setDiscountType(dto.getDiscountType());
                    existing.setValue(dto.getValue());
                    existing.setValidFrom(dto.getValidFrom());
                    existing.setValidTo(dto.getValidTo());
                    existing.setUsageLimit(dto.getUsageLimit());
                    existing.setTerms(dto.getTerms());
                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Promotion not found with id " + id));
    }

    public PromotionDTO save(PromotionDTO dto) {
        if (dto.getCode() == null || dto.getCode().isEmpty()) {
            throw new IllegalArgumentException("Promotion code is required");
        }
        if (dto.getValidFrom() != null && dto.getValidTo() != null &&
                dto.getValidFrom().isAfter(dto.getValidTo())) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        Promotion entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public BigDecimal calculateDiscount(UUID promotionId, BigDecimal originalPrice) {
        return repository.findById(promotionId)
                .map(promo -> {
                    PromotionStrategy strategy = strategyFactory.getStrategy(promo.getDiscountType());
                    return strategy.calculateDiscount(originalPrice, promo.getValue());
                })
                .orElse(BigDecimal.ZERO);
    }
}
