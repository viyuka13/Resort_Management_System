package com.resortmanagement.system.billing.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.billing.entity.AccountLedger;
import com.resortmanagement.system.billing.entity.AccountType;

/**
 * AccountLedgerRepository
 * Purpose:
 *  - Repository for AccountLedger entity operations
 * Methods:
 *  - findByAccountCode: Find ledger account by unique account code
 *  - findByAccountType: Find all ledger accounts of a specific type
 */
@Repository
public interface AccountLedgerRepository extends JpaRepository<AccountLedger, UUID> {
    
    Optional<AccountLedger> findByAccountCode(String accountCode);
    
    List<AccountLedger> findByAccountType(AccountType accountType);
}
