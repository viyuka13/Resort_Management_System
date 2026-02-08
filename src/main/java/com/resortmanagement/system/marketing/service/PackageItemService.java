package com.resortmanagement.system.marketing.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.marketing.entity.PackageItem;
import com.resortmanagement.system.marketing.repository.PackageItemRepository;

@Service
public class PackageItemService {

    private final PackageItemRepository repository;

    public PackageItemService(PackageItemRepository repository) {
        this.repository = repository;
    }

    public org.springframework.data.domain.Page<PackageItem> findAll(
            org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<PackageItem> findById(UUID id) {
        return repository.findById(id);
    }

    public PackageItem save(PackageItem entity) {
        if (entity.getPkg() == null) {
            throw new IllegalArgumentException("Package is required");
        }

        boolean hasItem = entity.getRoomType() != null ||
                entity.getServiceItem() != null ||
                entity.getMenuItem() != null ||
                entity.getInventoryItem() != null;

        if (!hasItem) {
            throw new IllegalArgumentException(
                    "At least one item reference (RoomType, ServiceItem, MenuItem, InventoryItem) is required");
        }

        if (entity.getQty() == null || entity.getQty() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (entity.getPrice() == null || entity.getPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return repository.save(entity);
    }

    public PackageItem update(UUID id, PackageItem entity) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setPkg(entity.getPkg());
                    existing.setRoomType(entity.getRoomType());
                    existing.setServiceItem(entity.getServiceItem());
                    existing.setMenuItem(entity.getMenuItem());
                    existing.setInventoryItem(entity.getInventoryItem());
                    existing.setQty(entity.getQty());
                    existing.setPrice(entity.getPrice());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("PackageItem not found with id " + id));
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
