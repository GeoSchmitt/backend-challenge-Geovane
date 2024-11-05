package com.backend_challenge_geovane.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CustomerDto {
    private UUID id;
    private String name;
    private String address;

}
