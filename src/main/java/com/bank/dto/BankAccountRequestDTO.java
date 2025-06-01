package com.bank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BankAccountRequestDTO {

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @Min(value = 0, message = "Balance cannot be negative")
    private Double balance = 0.0;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
