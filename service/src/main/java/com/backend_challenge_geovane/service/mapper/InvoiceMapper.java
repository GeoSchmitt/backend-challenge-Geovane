package com.backend_challenge_geovane.service.mapper;

import com.backend_challenge_geovane.service.dto.InvoiceDto;
import com.backend_challenge_geovane.service.model.InvoiceEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    private CustomerMapper customerMapper;
    private InvoicePositionMapper invoicePositionMapper;

    public InvoiceMapper(CustomerMapper customerMapper, InvoicePositionMapper invoicePositionMapper) {
        this.customerMapper = customerMapper;
        this.invoicePositionMapper = invoicePositionMapper;
    }

    public InvoiceEntity toEntity(InvoiceDto invoiceDto){
        var customer = customerMapper.toEntity(invoiceDto.getCustomer());
        var invoicePosition = invoicePositionMapper.toEntityList(invoiceDto.getPositions());
        return new InvoiceEntity(invoiceDto.getId(),
                invoiceDto.getCode(),
                invoiceDto.getTitle(),
                invoiceDto.getDescription(),
                invoiceDto.getIssuedAt(),
                customer,
                invoicePosition,
                invoiceDto.getTotalAmount());
    }

    public InvoiceDto toDto(InvoiceEntity invoiceEntity) {
        var customer = customerMapper.toDto(invoiceEntity.getCustomer());
        var invoicePosition = invoicePositionMapper.toDtoList(invoiceEntity.getPositions());
        return new InvoiceDto(invoiceEntity.getId(),
                invoiceEntity.getCode(),
                invoiceEntity.getTitle(),
                invoiceEntity.getDescription(),
                invoiceEntity.getIssuedAt(),
                customer,
                invoicePosition,
                invoiceEntity.getTotalAmount());
    }
}
