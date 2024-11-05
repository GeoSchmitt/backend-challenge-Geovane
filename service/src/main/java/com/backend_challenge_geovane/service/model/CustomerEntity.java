package com.backend_challenge_geovane.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CustomerEntity {
    private UUID id;
    private String name;
    private String address;
}
