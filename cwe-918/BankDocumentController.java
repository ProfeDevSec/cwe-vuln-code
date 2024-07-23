package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class BankDocumentController {

    @Autowired
    private HttpClient httpClient;

    private static final List<String> ALLOWED_DOMAINS = List.of("trusteddomain.com", "anothertrusteddomain.com");

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchDocument(@RequestParam String url) throws Exception {
        if (!isValidUrl(url)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid URL");
        }
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok(response.body());
    }

    private boolean isValidUrl(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            return ALLOWED_DOMAINS.stream().anyMatch(host::endsWith);
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
