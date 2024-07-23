package com.example.bank.controller;

import com.example.bank.model.TransferRequest;
import com.example.bank.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/create")
    public ResponseEntity<String> createTransfer(@RequestBody TransferRequest transferRequest) {
        transferService.createTransfer(transferRequest);
        return ResponseEntity.ok("Transfer created successfully");
    }
}
