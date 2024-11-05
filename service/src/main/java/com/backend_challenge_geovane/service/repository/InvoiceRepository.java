package com.backend_challenge_geovane.service.repository;

import com.backend_challenge_geovane.service.model.InvoiceEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InvoiceRepository {

    private HashMap<UUID, InvoiceEntity> DB_INVOICE = new HashMap<>();

    public void save(InvoiceEntity invoiceEntity){
        DB_INVOICE.put(invoiceEntity.getId(), invoiceEntity);
    }

    public Optional<InvoiceEntity> getById(UUID invoiceId) {
       return Optional.ofNullable(DB_INVOICE.get(invoiceId));
    }

    public List<InvoiceEntity> getAllByCustomerId(UUID customerId){
        return DB_INVOICE.values().stream().filter(invoiceEntity -> invoiceEntity.getCustomer().getId().equals(customerId)).toList();
    }
}
