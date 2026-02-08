package com.resortmanagement.system.billing.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.billing.entity.AccountLedger;
import com.resortmanagement.system.billing.entity.AccountType;
import com.resortmanagement.system.billing.repository.AccountLedgerRepository;
import com.resortmanagement.system.common.exception.ApplicationException;

/**
 * AccountLedgerService
 * Purpose:
 *  - Service layer for AccountLedger entity operations
 *  - Handles ledger account creation and balance updates
 * Business Logic:
 *  - updateBalance: Transactionally updates ledger account balance
 *  - Uses optimistic locking (@Version) to prevent concurrent update issues
 *  - Validates account operations
 */
@Service
@Transactional
public class AccountLedgerService {

    private final AccountLedgerRepository repository;

    public AccountLedgerService(AccountLedgerRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<AccountLedger> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<AccountLedger> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<AccountLedger> findByAccountCode(String accountCode) {
        return repository.findByAccountCode(accountCode);
    }

    @Transactional(readOnly = true)
    public List<AccountLedger> findByAccountType(AccountType accountType) {
        return repository.findByAccountType(accountType);
    }

    public AccountLedger save(AccountLedger ledger) {
        // Validation: ensure required fields are present
        if (ledger.getAccountCode() == null || ledger.getAccountCode().trim().isEmpty()) {
            throw new ApplicationException("Account code is required");
        }
        if (ledger.getName() == null || ledger.getName().trim().isEmpty()) {
            throw new ApplicationException("Account name is required");
        }
        if (ledger.getAccountType() == null) {
            throw new ApplicationException("Account type is required");
        }
        
        // Check for duplicate account code
        if (ledger.getId() == null) {
            Optional<AccountLedger> existing = repository.findByAccountCode(ledger.getAccountCode());
            if (existing.isPresent()) {
                throw new ApplicationException("Account with code already exists: " + ledger.getAccountCode());
            }
        }
        
        return repository.save(ledger);
    }

    public AccountLedger updateBalance(UUID ledgerId, BigDecimal amount) {
        AccountLedger ledger = repository.findById(ledgerId)
                .orElseThrow(() -> new ApplicationException("Account ledger not found with id: " + ledgerId));
        
        if (amount == null) {
            throw new ApplicationException("Amount cannot be null");
        }
        
        BigDecimal currentBalance = ledger.getBalance() != null ? ledger.getBalance() : BigDecimal.ZERO;
        ledger.setBalance(currentBalance.add(amount));
        
        return repository.save(ledger);
    }

    public void deleteById(UUID id) {
        // Note: Consider preventing deletion of ledger accounts with non-zero balances
        AccountLedger ledger = repository.findById(id)
                .orElseThrow(() -> new ApplicationException("Account ledger not found with id: " + id));
        
        if (ledger.getBalance() != null && ledger.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new ApplicationException("Cannot delete account ledger with non-zero balance");
        }
        
        repository.deleteById(id);
    }
}
