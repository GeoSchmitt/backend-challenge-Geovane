package com.backend_challenge_geovane.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class InvoiceEntity {
    private UUID id;
    private String code;
    private String title;
    private String description;
    private LocalDateTime issuedAt;
    private CustomerEntity customer;
    private List<InvoicePositionEntity> positions;
    private float totalAmount;
}
