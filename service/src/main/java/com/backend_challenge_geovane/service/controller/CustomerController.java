package com.backend_challenge_geovane.service.controller;

import com.backend_challenge_geovane.service.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private InvoiceService invoiceService;

    @Autowired
    public CustomerController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping("/{id}/invoiceTotalCurrentMonth")
    public ResponseEntity<Float> createInvoice(@PathVariable("id") String id) {
        final UUID uuid = UUID.fromString(id);
        var response = invoiceService.getCurrentMonthAmount(uuid);
        return new ResponseEntity<>(response, OK);
    }


}
