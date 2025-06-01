package com.bank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AmountDTO {

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be greater than zero")
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
