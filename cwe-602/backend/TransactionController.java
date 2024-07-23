package com.example.bank.controller;

import com.example.bank.model.Transaction;
import com.example.bank.service.TransactionService;
import com.example.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction, Authentication authentication) {
        String username = authentication.getName();
        
        if (!userService.hasPermission(username, "CREATE_TRANSACTION")) {
            return ResponseEntity.status(403).body("Forbidden");
        }
        
        transactionService.createTransaction(transaction);
        return ResponseEntity.ok("Transaction created successfully");
    }
}
