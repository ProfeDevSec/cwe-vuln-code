package com.example.bank.service;

import com.example.bank.model.ImmutableTransferRequest;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    public void createTransfer(ImmutableTransferRequest transferRequest) {
        // Implementar lógica de transferencia usando un objeto inmutable
        System.out.println("Transferring " + transferRequest.getAmount() + " from " +
                           transferRequest.getFromAccount() + " to " + transferRequest.getToAccount());
    }
}
