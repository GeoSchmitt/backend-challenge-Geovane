package com.backend_challenge_geovane.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class InvoiceDto {
    private UUID id;
    private String code;
    private String title;
    private String description;
    private LocalDateTime issuedAt;
    private CustomerDto customer;
    private List<InvoicePositionDto> positions;
    private float totalAmount;
}
