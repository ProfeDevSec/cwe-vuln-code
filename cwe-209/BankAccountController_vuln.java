package com.example.bank.controller;

import com.example.bank.model.BankAccount;
import com.example.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    // CWE-209: Generation of Error Message Containing Sensitive Information
    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable Long accountId) {
        try {
            BankAccount account = bankAccountService.findById(accountId);
            if (account == null) {
                return ResponseEntity.status(404).body(null);
            }
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            // Including stack trace and sensitive information in the error message
            return ResponseEntity.status(500).body(e.getMessage()); // CWE-209
        }
    }
}
