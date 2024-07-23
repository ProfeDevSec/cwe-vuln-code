package com.example.bank.service;

import com.example.bank.model.BankAccount;
import com.example.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount findById(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }
}
