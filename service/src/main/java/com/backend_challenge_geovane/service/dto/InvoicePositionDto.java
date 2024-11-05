package com.backend_challenge_geovane.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoicePositionDto {
    private String description;
    private float amount;
}
