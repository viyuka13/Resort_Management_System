/*
TODO: Marketing repository & services guidelines
Services:
 - PackageService.bookPackage(reservationId, packageId) -> expand into reservation components (transactional)
 - PromotionService.validateAndApply(promoCode, reservation) -> return adjusted price or throw PromotionInvalidException
File: marketing/repository/<File>.java, marketing/service/<File>.java
*/
package com.resortmanagement.system.marketing.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.marketing.entity.LoyaltyMember;
import com.resortmanagement.system.marketing.repository.LoyaltyMemberRepository;

@Service
public class LoyaltyMemberService {

    private final LoyaltyMemberRepository repository;

    public LoyaltyMemberService(LoyaltyMemberRepository repository) {
        this.repository = repository;
    }

    public org.springframework.data.domain.Page<LoyaltyMember> findAll(
            org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<LoyaltyMember> findById(UUID id) {
        return repository.findById(id);
    }

    public LoyaltyMember save(LoyaltyMember entity) {
        if (entity.getGuest() == null || entity.getGuest().getId() == null) {
            throw new IllegalArgumentException("Guest is required");
        }
        if (entity.getPointsBalance() == null) {
            entity.setPointsBalance(BigDecimal.ZERO);
        }
        if (entity.getTier() == null) {
            entity.setTier("MEMBER"); // Default tier
        }
        return repository.save(entity);
    }

    public LoyaltyMember update(UUID id, LoyaltyMember entity) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setGuest(entity.getGuest());
                    existing.setPointsBalance(entity.getPointsBalance());
                    existing.setTier(entity.getTier());
                    existing.setEnrolledAt(entity.getEnrolledAt());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("LoyaltyMember not found with id " + id));
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Transactional
    public void awardPoints(UUID memberId, BigDecimal amount) {
        findById(memberId).ifPresent(member -> {
            member.setPointsBalance(member.getPointsBalance().add(amount));
            checkTierUpgrade(member);
            save(member);
        });
    }

    private void checkTierUpgrade(LoyaltyMember member) {
        BigDecimal balance = member.getPointsBalance();
        if (balance.compareTo(new BigDecimal("10000")) >= 0) {
            member.setTier("PLATINUM");
        } else if (balance.compareTo(new BigDecimal("5000")) >= 0) {
            member.setTier("GOLD");
        } else if (balance.compareTo(new BigDecimal("1000")) >= 0) {
            member.setTier("SILVER");
        }
    }
}
