package com.resortmanagement.system.marketing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;

@Entity
@Table(name = "package_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageItem extends AuditableSoftDeletable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    @ToString.Exclude
    private Package pkg;

    @Column(name = "room_type_id")
    private Long roomTypeId;

    @Column(name = "service_item_id")
    private Long serviceItemId;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "inventory_item_id")
    private Long inventoryItemId;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}