package com.resortmanagement.system.marketing.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.marketing.dto.MarketingMapper;
import com.resortmanagement.system.marketing.dto.PackageItemDTO;
import com.resortmanagement.system.marketing.entity.PackageItem;
import com.resortmanagement.system.marketing.repository.PackageItemRepository;
import com.resortmanagement.system.marketing.repository.PackageRepository;

@Service
@Transactional
public class PackageItemService {

    private final PackageItemRepository repository;
    private final PackageRepository packageRepository;
    // External repositories removed to avoid context failure on stub entities
    private final MarketingMapper mapper;

    public PackageItemService(
            PackageItemRepository repository,
            PackageRepository packageRepository,
            MarketingMapper mapper) {
        this.repository = repository;
        this.packageRepository = packageRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<PackageItemDTO> findAll(
            org.springframework.data.domain.Pageable pageable) {
        return repository.findByDeletedFalse(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<PackageItemDTO> findById(UUID id) {
        return repository.findByIdAndDeletedFalse(id).map(mapper::toDTO);
    }

    public PackageItemDTO save(PackageItemDTO dto) {
        if (dto.getPkgId() == null) {
            throw new IllegalArgumentException("Package ID is required");
        }

        PackageItem entity = new PackageItem();

        // Resolve package
        entity.setPkg(packageRepository.findById(dto.getPkgId())
                .orElseThrow(() -> new IllegalArgumentException("Package not found")));

        // Set optional item IDs directly
        // Note: Validation of existence is skipped as external modules are currently
        // stubs
        entity.setRoomTypeId(dto.getRoomTypeId());
        entity.setServiceItemId(dto.getServiceItemId());
        entity.setMenuItemId(dto.getMenuItemId());
        entity.setInventoryItemId(dto.getInventoryItemId());

        boolean hasItem = entity.getRoomTypeId() != null ||
                entity.getServiceItemId() != null ||
                entity.getMenuItemId() != null ||
                entity.getInventoryItemId() != null;

        if (!hasItem) {
            throw new IllegalArgumentException(
                    "At least one item reference (RoomType, ServiceItem, MenuItem, InventoryItem) is required");
        }

        if (dto.getQty() == null || dto.getQty() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        entity.setQty(dto.getQty());

        if (dto.getPrice() == null || dto.getPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        entity.setPrice(dto.getPrice());

        return mapper.toDTO(repository.save(entity));
    }

    public PackageItemDTO update(UUID id, PackageItemDTO dto) {
        return repository.findByIdAndDeletedFalse(id)
                .map(existing -> {
                    // Update quantity and price
                    mapper.updateEntityFromDTO(existing, dto);

                    // Update relationships if provided
                    if (dto.getPkgId() != null) {
                        existing.setPkg(packageRepository.findById(dto.getPkgId())
                                .orElseThrow(() -> new IllegalArgumentException("Package not found")));
                    }

                    if (dto.getRoomTypeId() != null)
                        existing.setRoomTypeId(dto.getRoomTypeId());
                    if (dto.getServiceItemId() != null)
                        existing.setServiceItemId(dto.getServiceItemId());
                    if (dto.getMenuItemId() != null)
                        existing.setMenuItemId(dto.getMenuItemId());
                    if (dto.getInventoryItemId() != null)
                        existing.setInventoryItemId(dto.getInventoryItemId());

                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("PackageItem not found with id " + id));
    }

    public void deleteById(UUID id) {
        repository.softDeleteById(id, java.time.Instant.now());
    }
}
