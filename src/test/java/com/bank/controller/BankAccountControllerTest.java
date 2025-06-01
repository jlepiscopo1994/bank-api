package com.bank.controller;

import com.bank.entity.BankAccount;
import com.bank.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BankAccountRepository repo;

    @BeforeEach
    void setup() {
        // Code to set up initial state, if necessary
        repo.deleteAll();
        repo.save(new BankAccount(null, "Test User", 100.0));
    }

    @Test
    void deposit_withNegativeAmount_shouldReturn400() throws Exception {
        String body = "{\"amount\": -100}";
        mockMvc.perform(
            post("/api/accounts/1/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isBadRequest());
    }

    @Test
    void deposit_withValidAmount_shouldReturn200() throws Exception {
        String body = "{\"amount\": 100}";
        mockMvc.perform(
            post("/api/accounts/1/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isOk());
    }

}
