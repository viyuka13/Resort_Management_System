/*
TODO: Marketing repository & services guidelines
Services:
 - PackageService.bookPackage(reservationId, packageId) -> expand into reservation components (transactional)
 - PromotionService.validateAndApply(promoCode, reservation) -> return adjusted price or throw PromotionInvalidException
File: marketing/repository/<File>.java, marketing/service/<File>.java
*/
package com.resortmanagement.system.marketing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.marketing.entity.LoyaltyMember;
import com.resortmanagement.system.marketing.repository.LoyaltyMemberRepository;

@Service
public class LoyaltyMemberService {

    private final LoyaltyMemberRepository repository;

    public LoyaltyMemberService(LoyaltyMemberRepository repository) {
        this.repository = repository;
    }

    public List<LoyaltyMember> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<LoyaltyMember> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public LoyaltyMember save(LoyaltyMember entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
