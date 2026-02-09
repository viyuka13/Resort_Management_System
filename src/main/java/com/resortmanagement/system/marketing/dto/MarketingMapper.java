package com.resortmanagement.system.marketing.dto;

import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.Collections;

import com.resortmanagement.system.marketing.entity.Promotion;
import com.resortmanagement.system.marketing.entity.Package;
import com.resortmanagement.system.marketing.entity.PackageItem;
import com.resortmanagement.system.marketing.entity.LoyaltyMember;

@Component
public class MarketingMapper {

    // Promotion
    public PromotionDTO toDTO(Promotion entity) {
        if (entity == null)
            return null;
        return PromotionDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .description(entity.getDescription())
                .discountType(entity.getDiscountType())
                .value(entity.getValue())
                .validFrom(entity.getValidFrom())
                .validTo(entity.getValidTo())
                .usageLimit(entity.getUsageLimit())
                .terms(entity.getTerms())
                .build();
    }

    public Promotion toEntity(PromotionDTO dto) {
        if (dto == null)
            return null;
        return Promotion.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .description(dto.getDescription())
                .discountType(dto.getDiscountType())
                .value(dto.getValue())
                .validFrom(dto.getValidFrom())
                .validTo(dto.getValidTo())
                .usageLimit(dto.getUsageLimit())
                .terms(dto.getTerms())
                .build();
    }

    // Package
    public PackageDTO toDTO(Package entity) {
        if (entity == null)
            return null;
        return PackageDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .validFrom(entity.getValidFrom())
                .validTo(entity.getValidTo())
                .usageLimit(entity.getUsageLimit())
                .items(entity.getItems() != null
                        ? entity.getItems().stream().map(this::toDTO).collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public Package toEntity(PackageDTO dto) {
        if (dto == null)
            return null;
        return Package.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .validFrom(dto.getValidFrom())
                .validTo(dto.getValidTo())
                .usageLimit(dto.getUsageLimit())
                // Items handled separately / by service to ensure relationships
                .build();
    }

    // PackageItem
    public PackageItemDTO toDTO(PackageItem entity) {
        if (entity == null)
            return null;
        return PackageItemDTO.builder()
                .id(entity.getId())
                .pkgId(entity.getPkg() != null ? entity.getPkg().getId() : null)
                .pkgName(entity.getPkg() != null ? entity.getPkg().getName() : null)
                .roomTypeId(entity.getRoomTypeId())
                .roomTypeName(null) // Name not available from ID only
                .serviceItemId(entity.getServiceItemId())
                .serviceItemName(null)
                .menuItemId(entity.getMenuItemId())
                .menuItemName(null)
                .inventoryItemId(entity.getInventoryItemId())
                .inventoryItemName(null)
                .qty(entity.getQty())
                .price(entity.getPrice())
                .build();
    }

    public void updateEntityFromDTO(PackageItem entity, PackageItemDTO dto) {
        if (dto == null || entity == null)
            return;
        entity.setQty(dto.getQty());
        entity.setPrice(dto.getPrice());
        // Relationships should be handled by service
    }

    // LoyaltyMember
    public LoyaltyMemberDTO toDTO(LoyaltyMember entity) {
        if (entity == null)
            return null;

        // Guest name resolution should be handled by service if needed
        return LoyaltyMemberDTO.builder()
                .id(entity.getId())
                .guestId(entity.getGuestId())
                .guestName(null)
                .tier(entity.getTier())
                .pointsBalance(entity.getPointsBalance())
                .enrolledAt(entity.getEnrolledAt())
                .status(entity.getStatus())
                .build();
    }

    public LoyaltyMember toEntity(LoyaltyMemberDTO dto) {
        if (dto == null)
            return null;
        return LoyaltyMember.builder()
                .id(dto.getId())
                .guestId(dto.getGuestId())
                .tier(dto.getTier())
                .pointsBalance(dto.getPointsBalance())
                .enrolledAt(dto.getEnrolledAt())
                .status(dto.getStatus())
                .build();
    }
}
