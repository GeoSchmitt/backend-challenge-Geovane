package com.backend_challenge_geovane.service.service;

import com.backend_challenge_geovane.service.dto.InvoiceDto;
import com.backend_challenge_geovane.service.mapper.InvoiceMapper;
import com.backend_challenge_geovane.service.model.InvoiceEntity;
import com.backend_challenge_geovane.service.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService {

    private InvoiceMapper invoiceMapper;

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceMapper invoiceMapper, InvoiceRepository invoiceRepository) {
        this.invoiceMapper = invoiceMapper;
        this.invoiceRepository = invoiceRepository;
    }

    public InvoiceDto createInvoice(InvoiceDto invoiceDto){
        var entity = invoiceMapper.toEntity(invoiceDto);
        invoiceRepository.save(entity);
        return invoiceDto;
    }

    public Optional<InvoiceEntity> retrieveInvoice(UUID invoiceId) {
        final Optional<InvoiceEntity> result = invoiceRepository.getById(invoiceId);
        return result;
    }

    public Float getCurrentMonthAmount(UUID customerId){
        final YearMonth currentMonth = YearMonth.now();
        final List<InvoiceEntity> customerInvoices = invoiceRepository.getAllByCustomerId(customerId);
        return customerInvoices.stream()
                .filter(invoiceEntity -> YearMonth.from(invoiceEntity.getIssuedAt()).equals(currentMonth))
                .map(InvoiceEntity::getTotalAmount)
                .reduce(0.0f, Float::sum);
    }
}
