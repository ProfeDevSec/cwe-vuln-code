package com.example.bank.controller;

import com.example.bank.model.BankAccount;
import com.example.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    // CWE-285: Improper Authorization
    // No checks to ensure the authenticated user is accessing their own account
    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable Long accountId) {
        BankAccount account = bankAccountService.findById(accountId);
        return ResponseEntity.ok(account);
    }

    // CWE-285: Improper Authorization
    // No checks to ensure the authenticated user is accessing their own accounts
    @GetMapping("/user")
    public ResponseEntity<List<BankAccount>> getAccountsForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<BankAccount> accounts = bankAccountService.findByUsername(username);
        return ResponseEntity.ok(accounts);
    }
}
