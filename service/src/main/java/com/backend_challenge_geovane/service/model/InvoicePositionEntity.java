package com.backend_challenge_geovane.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoicePositionEntity {
    private String description;
    private float amount;
}
