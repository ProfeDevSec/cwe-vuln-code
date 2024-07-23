package com.example.bank.controller;

import com.example.bank.model.BankAccount;
import com.example.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    private static final Logger logger = Logger.getLogger(BankAccountController.class.getName());

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable Long accountId) {
        try {
            BankAccount account = bankAccountService.findById(accountId);
            if (account == null) {
                return ResponseEntity.status(404).body(null);
            }
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving account with ID: " + accountId, e);
            // Return a generic error message without sensitive information
            return ResponseEntity.status(500).body(null);
        }
    }
}
