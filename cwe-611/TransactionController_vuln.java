package com.example.bank.controller;

import com.example.bank.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
import org.xml.sax.InputSource;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody String xmlTransaction) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlTransaction)));

            // Procesar el documento XML aquí

            return ResponseEntity.ok("Transaction created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing transaction");
        }
    }
}
