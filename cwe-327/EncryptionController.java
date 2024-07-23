package com.example.bank.controller;

import com.example.bank.service.SecureEncryptionService;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

    private final SecureEncryptionService encryptionService;
    private final SecretKey secretKey;

    public EncryptionController(SecureEncryptionService encryptionService) throws Exception {
        this.encryptionService = encryptionService;
        this.secretKey = encryptionService.generateKey();
    }

    @PostMapping("/encrypt")
    public Map<String, String> encrypt(@RequestBody Map<String, String> request) throws Exception {
        String data = request.get("data");
        String encryptedData = encryptionService.encrypt(data, secretKey);

        Map<String, String> response = new HashMap<>();
        response.put("encryptedData", encryptedData);
        return response;
    }

    @PostMapping("/decrypt")
    public Map<String, String> decrypt(@RequestBody Map<String, String> request) throws Exception {
        String encryptedData = request.get("encryptedData");
        String decryptedData = encryptionService.decrypt(encryptedData, secretKey);

        Map<String, String> response = new HashMap<>();
        response.put("decryptedData", decryptedData);
        return response;
    }
}
