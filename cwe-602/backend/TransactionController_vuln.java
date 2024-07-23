package com.example.bank.controller;

import com.example.bank.model.Transaction;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // CWE-602: Client-Side Enforcement of Server-Side Security
    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction, @RequestParam boolean hasPermission) {
        if (!hasPermission) {
            return ResponseEntity.status(403).body("Forbidden");
        }
        transactionService.createTransaction(transaction);
        return ResponseEntity.ok("Transaction created successfully");
    }
}
