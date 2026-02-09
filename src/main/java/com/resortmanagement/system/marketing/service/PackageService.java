package com.resortmanagement.system.marketing.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.marketing.dto.MarketingMapper;
import com.resortmanagement.system.marketing.dto.PackageDTO;
import com.resortmanagement.system.marketing.entity.Package;
import com.resortmanagement.system.marketing.repository.PackageRepository;

@Service
@Transactional
public class PackageService {

    private final PackageRepository repository;
    private final MarketingMapper mapper;

    public PackageService(PackageRepository repository, MarketingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<PackageDTO> findAll(org.springframework.data.domain.Pageable pageable) {
        return repository.findByDeletedFalse(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<PackageDTO> findById(UUID id) {
        return repository.findByIdAndDeletedFalse(id).map(mapper::toDTO);
    }

    public PackageDTO update(UUID id, PackageDTO dto) {
        return repository.findByIdAndDeletedFalse(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    existing.setPrice(dto.getPrice());
                    existing.setValidFrom(dto.getValidFrom());
                    existing.setValidTo(dto.getValidTo());
                    existing.setUsageLimit(dto.getUsageLimit());
                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Package not found with id " + id));
    }

    public PackageDTO save(PackageDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Package name is required");
        }
        if (dto.getPrice() == null || dto.getPrice().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        Package entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public void deleteById(UUID id) {
        repository.softDeleteById(id, java.time.Instant.now());
    }
}
