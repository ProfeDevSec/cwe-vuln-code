package com.example.bank.controller;

import com.example.bank.model.Transaction;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Correct authorization check to ensure the authenticated user is accessing their own transactions
    @GetMapping("/user")
    public ResponseEntity<List<Transaction>> getTransactionsForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<Transaction> transactions = transactionService.findByUsername(username);

        if (transactions == null || transactions.isEmpty()) {
            return ResponseEntity.status(403).build(); // Return 403 Forbidden if unauthorized
        }

        return ResponseEntity.ok(transactions);
    }
}
