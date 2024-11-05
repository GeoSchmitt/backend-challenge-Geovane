package com.backend_challenge_geovane.service.mapper;

import com.backend_challenge_geovane.service.dto.InvoicePositionDto;
import com.backend_challenge_geovane.service.model.InvoicePositionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoicePositionMapper {

    public List<InvoicePositionEntity> toEntityList(List<InvoicePositionDto> invoicePositionDtoList){
        return invoicePositionDtoList.stream().map(InvoicePositionMapper::toEntity).collect(Collectors.toList());
    }

    public List<InvoicePositionDto> toDtoList(List<InvoicePositionEntity> invoicePositionEntityList){
        return invoicePositionEntityList.stream().map(InvoicePositionMapper::toDto).collect(Collectors.toList());
    }

    private static InvoicePositionEntity toEntity(InvoicePositionDto invoicePositionDto){
        return new InvoicePositionEntity(invoicePositionDto.getDescription(), invoicePositionDto.getAmount());
    }

    private static InvoicePositionDto toDto(InvoicePositionEntity invoicePositionEntity){
        return new InvoicePositionDto(invoicePositionEntity.getDescription(), invoicePositionEntity.getAmount());
    }
}
