package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> { }
