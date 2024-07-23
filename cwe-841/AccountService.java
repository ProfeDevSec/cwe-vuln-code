package com.example.bank.service;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public boolean isAccountEligibleForTransfer(String accountNumber) {
        // Lógica para validar el estado de la cuenta y el historial de transacciones
        // Por ejemplo, verificar si la cuenta está activa, si no hay bloqueos, etc.
        
        // Supongamos que hacemos una consulta a la base de datos para validar
        boolean accountActive = checkAccountStatus(accountNumber);
        boolean noPendingIssues = checkPendingIssues(accountNumber);

        return accountActive && noPendingIssues;
    }

    private boolean checkAccountStatus(String accountNumber) {
        // Lógica para verificar el estado de la cuenta en la base de datos
        return true; // Ejemplo simplificado
    }

    private boolean checkPendingIssues(String accountNumber) {
        // Lógica para verificar problemas pendientes en la cuenta
        return true; // Ejemplo simplificado
    }
}
