package com.resortmanagement.system.marketing.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.resortmanagement.system.marketing.entity.LoyaltyMember.MemberStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyMemberDTO {
    private UUID id;
    private UUID guestId;
    private String guestName;
    private String tier;
    private BigDecimal pointsBalance;
    private Instant enrolledAt;
    private MemberStatus status;
}
