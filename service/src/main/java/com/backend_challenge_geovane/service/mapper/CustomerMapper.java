package com.backend_challenge_geovane.service.mapper;

import com.backend_challenge_geovane.service.dto.CustomerDto;
import com.backend_challenge_geovane.service.dto.InvoiceDto;
import com.backend_challenge_geovane.service.model.CustomerEntity;
import com.backend_challenge_geovane.service.model.InvoiceEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {


    public CustomerEntity toEntity(CustomerDto customerDto){
        return new CustomerEntity(customerDto.getId(), customerDto.getName(), customerDto.getAddress());
    }

    public CustomerDto toDto(CustomerEntity customerEntity) {
        return new CustomerDto(customerEntity.getId(), customerEntity.getName(), customerEntity.getAddress());
    }
}
