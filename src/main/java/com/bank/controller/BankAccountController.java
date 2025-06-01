package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.AmountDTO;
import com.bank.dto.BankAccountRequestDTO;
import com.bank.dto.BankAccountResponseDTO;
import com.bank.service.BankAccountService;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/accounts")
@Validated
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccountResponseDTO> getAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @PostMapping
    public BankAccountResponseDTO createAccount(@Valid @RequestBody BankAccountRequestDTO account) {
        return bankAccountService.createBankAccount(account);
    }

    @PostMapping("/{id}/deposit")
    public BankAccountResponseDTO deposit(@PathVariable Long id, @Valid @RequestBody AmountDTO amount) {
        System.out.println("Received amount: " + amount.getAmount());
        return bankAccountService.deposit(id, amount.getAmount());
    }
    
    @PostMapping("/{id}/withdraw")
    public BankAccountResponseDTO withdraw(@PathVariable Long id, @Valid @RequestBody AmountDTO amount) {
        System.out.println("Received amount: " + amount.getAmount());
        return bankAccountService.withdraw(id, amount.getAmount());
    }
}
