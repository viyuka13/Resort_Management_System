package com.resortmanagement.system.marketing.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.resortmanagement.system.marketing.entity.Promotion.DiscountType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
    private UUID id;
    private String code;
    private String description;
    private DiscountType discountType;
    private BigDecimal value;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Integer usageLimit;
    private String terms;
}
