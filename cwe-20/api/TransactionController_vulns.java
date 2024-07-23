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

    // CWE-20: Improper Input Validation
    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) {
        // No validation on the amount or account number
        transactionService.createTransaction(transaction);
        return ResponseEntity.ok("Transaction created successfully");
    }
}
