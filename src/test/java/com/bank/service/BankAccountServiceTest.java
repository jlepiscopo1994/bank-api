package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bank.dto.BankAccountResponseDTO;
import com.bank.entity.BankAccount;
import com.bank.repository.BankAccountRepository;

public class BankAccountServiceTest {

    @Test
    void deposit_shouldAddAmount() {
        BankAccountRepository repository = Mockito.mock(BankAccountRepository.class);
        BankAccountService service = new BankAccountService(repository);
        BankAccount acc = new BankAccount(1L, "name", 100.0);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(acc));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));

        BankAccountResponseDTO result = service.deposit(1L, 50.0);

        assertEquals(150.0, result.getBalance());
    }

    
    @Test
    void withdraw_shouldSubtractAmount() {
        BankAccountRepository repository = Mockito.mock(BankAccountRepository.class);
        BankAccountService service = new BankAccountService(repository);
        BankAccount acc = new BankAccount(1L, "name", 100.0);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(acc));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));

        BankAccountResponseDTO result = service.withdraw(1L, 50.0);

        assertEquals(50.0, result.getBalance());
    }

    @Test
    void withdraw_shouldThrowInsufficientBalance() {
        BankAccountRepository repository = Mockito.mock(BankAccountRepository.class);
        BankAccountService service = new BankAccountService(repository);
        BankAccount acc = new BankAccount(1L, "name", 20.0);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(acc));

        Exception ex = assertThrows(RuntimeException.class, () -> service.withdraw(1L, 50.0));
        assertTrue(ex.getMessage().contains("Insufficient balance"));
        
    }

}
