package com.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.dto.BankAccountRequestDTO;
import com.bank.dto.BankAccountResponseDTO;
import com.bank.entity.BankAccount;
import com.bank.repository.BankAccountRepository;

@Service
public class BankAccountService {

    private BankAccountRepository bankAccountRepository;

    BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccountResponseDTO> getAllBankAccounts() {
        return bankAccountRepository.findAll().stream()
                .map(this::mapToBankAccountResponseDTO)
                .toList();
    }


    public BankAccountResponseDTO createBankAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwnerName(bankAccountRequestDTO.getOwnerName());
        bankAccount.setBalance(bankAccountRequestDTO.getBalance());
        BankAccount createdBankAccount = bankAccountRepository.save(bankAccount);
        return mapToBankAccountResponseDTO(createdBankAccount);
    }

    public BankAccountResponseDTO deposit(Long id, Double amount) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        BankAccount updatedBankAccount = bankAccountRepository.save(bankAccount);
        return mapToBankAccountResponseDTO(updatedBankAccount);
    }

    public BankAccountResponseDTO withdraw(Long id, Double amount) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        BankAccount updatedBankAccount = bankAccountRepository.save(bankAccount);
        return mapToBankAccountResponseDTO(updatedBankAccount);
    }

    private BankAccountResponseDTO mapToBankAccountResponseDTO(BankAccount bankAccount) {
        return new BankAccountResponseDTO(bankAccount.getId(), bankAccount.getOwnerName(), bankAccount.getBalance());
    }
}
