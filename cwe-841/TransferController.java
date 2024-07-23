package com.example.bank.controller;

import com.example.bank.model.TransferRequest;
import com.example.bank.service.AccountService;
import com.example.bank.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<String> createTransfer(@RequestBody TransferRequest transferRequest) {
        // Validar el estado de la cuenta y el historial de transacciones
        if (accountService.isAccountEligibleForTransfer(transferRequest.getFromAccount())) {
            transferService.createTransfer(transferRequest);
            return ResponseEntity.ok("Transfer created successfully");
        } else {
            return ResponseEntity.status(400).body("Account is not eligible for transfer");
        }
    }
}

