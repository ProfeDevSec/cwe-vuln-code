package com.example.bank.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Simulación de la validación de permisos (en un entorno real, se consultaría la base de datos o un servicio externo)
    public boolean hasPermission(String username, String permission) {
        // Implementar lógica para verificar permisos del usuario
        return "admin".equals(username) && "CREATE_TRANSACTION".equals(permission);
    }
}
