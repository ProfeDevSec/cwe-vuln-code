package com.example.bank.controller;

import com.example.bank.model.BankAccount;
import com.example.bank.service.BankAccountService;
import com.example.bank.service.UserService;
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

    @Autowired
    private UserService userService;

    // Correct authorization check to ensure the authenticated user is accessing their own account
    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable Long accountId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        BankAccount account = bankAccountService.findById(accountId);

        if (account == null || !account.getUsername().equals(username)) {
            return ResponseEntity.status(403).build(); // Return 403 Forbidden if unauthorized
        }
        return ResponseEntity.ok(account);
    }

    // Correct authorization check to ensure the authenticated user is accessing their own accounts
    @GetMapping("/user")
    public ResponseEntity<List<BankAccount>> getAccountsForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<BankAccount> accounts = bankAccountService.findByUsername(username);

        if (accounts == null || accounts.isEmpty()) {
            return ResponseEntity.status(403).build(); // Return 403 Forbidden if unauthorized
        }
        return ResponseEntity.ok(accounts);
    }
}
