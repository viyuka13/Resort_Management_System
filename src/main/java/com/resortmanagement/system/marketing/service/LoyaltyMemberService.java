package com.resortmanagement.system.marketing.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.marketing.dto.LoyaltyMemberDTO;
import com.resortmanagement.system.marketing.dto.MarketingMapper;
import com.resortmanagement.system.marketing.entity.LoyaltyMember;
import com.resortmanagement.system.marketing.repository.LoyaltyMemberRepository;

import com.resortmanagement.system.common.guest.GuestRepository;

@Service
@Transactional
public class LoyaltyMemberService {

    private final LoyaltyMemberRepository repository;
    private final GuestRepository guestRepository;
    private final MarketingMapper mapper;

    public LoyaltyMemberService(LoyaltyMemberRepository repository, GuestRepository guestRepository,
            MarketingMapper mapper) {
        this.repository = repository;
        this.guestRepository = guestRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<LoyaltyMemberDTO> findAll(
            org.springframework.data.domain.Pageable pageable) {
        return repository.findByDeletedFalse(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<LoyaltyMemberDTO> findById(UUID id) {
        return repository.findByIdAndDeletedFalse(id).map(mapper::toDTO);
    }

    public LoyaltyMemberDTO save(LoyaltyMemberDTO dto) {
        if (dto.getGuestId() == null) {
            throw new IllegalArgumentException("Guest ID is required");
        }

        // Verify guest exists (optional but recommended)
        if (!guestRepository.existsById(dto.getGuestId())) {
            throw new IllegalArgumentException("Guest not found with id " + dto.getGuestId());
        }

        LoyaltyMember entity = mapper.toEntity(dto);

        if (entity.getPointsBalance() == null) {
            entity.setPointsBalance(BigDecimal.ZERO);
        }
        if (entity.getTier() == null) {
            entity.setTier("MEMBER"); // Default tier
        }
        return mapper.toDTO(repository.save(entity));
    }

    public LoyaltyMemberDTO update(UUID id, LoyaltyMemberDTO dto) {
        return repository.findByIdAndDeletedFalse(id)
                .map(existing -> {
                    // Start of Update logic
                    // If guest ID changed
                    if (dto.getGuestId() != null && !existing.getGuestId().equals(dto.getGuestId())) {
                        if (!guestRepository.existsById(dto.getGuestId())) {
                            throw new IllegalArgumentException("Guest not found with id " + dto.getGuestId());
                        }
                        existing.setGuestId(dto.getGuestId());
                    }

                    if (dto.getPointsBalance() != null)
                        existing.setPointsBalance(dto.getPointsBalance());
                    if (dto.getTier() != null)
                        existing.setTier(dto.getTier());
                    if (dto.getEnrolledAt() != null)
                        existing.setEnrolledAt(dto.getEnrolledAt());
                    if (dto.getStatus() != null)
                        existing.setStatus(dto.getStatus());

                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("LoyaltyMember not found with id " + id));
    }

    public void deleteById(UUID id) {
        repository.softDeleteById(id, java.time.Instant.now());
    }

    @Transactional
    public void awardPoints(UUID memberId, BigDecimal amount) {
        repository.findByIdAndDeletedFalse(memberId).ifPresent(member -> {
            member.setPointsBalance(member.getPointsBalance().add(amount));
            checkTierUpgrade(member);
            repository.save(member);
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
