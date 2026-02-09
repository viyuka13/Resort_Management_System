package com.resortmanagement.system.billing.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.resortmanagement.system.billing.entity.AccountType;

import lombok.Data;

@Data
public class AccountLedgerResponse {
    private UUID id;
    private String accountCode;
    private String name;
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
}
