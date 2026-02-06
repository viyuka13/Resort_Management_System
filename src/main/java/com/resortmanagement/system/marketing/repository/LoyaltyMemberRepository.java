package com.resortmanagement.system.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.marketing.entity.LoyaltyMember;

@Repository
public interface LoyaltyMemberRepository extends JpaRepository<LoyaltyMember, Long> {
    // TODO: add custom queries if needed
}
