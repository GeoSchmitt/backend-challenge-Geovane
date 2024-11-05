package com.backend_challenge_geovane.service.controller;

import com.backend_challenge_geovane.service.dto.InvoiceDto;
import com.backend_challenge_geovane.service.mapper.InvoiceMapper;
import com.backend_challenge_geovane.service.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    private InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, InvoiceMapper invoiceMapper) {
        this.invoiceService = invoiceService;
        this.invoiceMapper = invoiceMapper;
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceDto invoiceDto){
        var response = invoiceService.createInvoice(invoiceDto);
        return new ResponseEntity<>(response, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> createInvoice(@PathVariable("id") String id) {
        final UUID uuid = UUID.fromString(id);
        var response = invoiceService.retrieveInvoice(uuid);
        if (response.isPresent()) {
            return new ResponseEntity<>(invoiceMapper.toDto(response.get()), OK);
        } else {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }


}
