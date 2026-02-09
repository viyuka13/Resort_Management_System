package com.resortmanagement.system.marketing.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageItemDTO {
    private UUID id;
    private UUID pkgId;
    private String pkgName;
    private Long roomTypeId;
    private String roomTypeName;
    private Long serviceItemId;
    private String serviceItemName;
    private Long menuItemId;
    private String menuItemName;
    private Long inventoryItemId;
    private String inventoryItemName;
    private Integer qty;
    private BigDecimal price;
}
