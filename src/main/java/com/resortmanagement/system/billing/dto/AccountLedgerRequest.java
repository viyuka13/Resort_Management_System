package com.resortmanagement.system.billing.dto;

import java.math.BigDecimal;

import com.resortmanagement.system.billing.entity.AccountType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountLedgerRequest {
    @NotBlank
    private String accountCode;
    @NotBlank
    private String name;
    @NotNull
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
}
