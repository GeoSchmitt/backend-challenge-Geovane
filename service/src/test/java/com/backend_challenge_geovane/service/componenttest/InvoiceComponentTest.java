package com.backend_challenge_geovane.service.componenttest;

import com.backend_challenge_geovane.service.dto.CustomerDto;
import com.backend_challenge_geovane.service.dto.InvoiceDto;
import com.backend_challenge_geovane.service.dto.InvoicePositionDto;
import com.backend_challenge_geovane.service.model.CustomerEntity;
import com.backend_challenge_geovane.service.model.InvoiceEntity;
import com.backend_challenge_geovane.service.model.InvoicePositionEntity;
import com.backend_challenge_geovane.service.repository.InvoiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class InvoiceComponentTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Test
    public void shouldSucceedCreationInvoice() throws Exception {
        final CustomerDto customerDto = new CustomerDto(UUID.fromString("987e6543-e21c-12d3-a456-426614174000"), "John Doe", "1234 Elm Street, Springfield");
        final InvoicePositionDto invoicePositionDto = new InvoicePositionDto("Consulting hours", 500.0F);
        final InvoiceDto invoiceDto = new InvoiceDto(
                UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                "INV-2024-001",
                "Consulting Services",
                "Invoice for consulting services provided",
                LocalDateTime.parse("2024-11-05T10:00:00"),
                customerDto,
                Arrays.asList(invoicePositionDto),
                600.0F
        );

        mockMvc.perform(post("/api/v1/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("123e4567-e89b-12d3-a456-426614174000")))
                .andExpect(jsonPath("$.code", is("INV-2024-001")))
                .andExpect(jsonPath("$.title", is("Consulting Services")))
                .andExpect(jsonPath("$.description", is("Invoice for consulting services provided")))
                .andExpect(jsonPath("$.issuedAt", is("2024-11-05T10:00:00")))

                // Customer fields
                .andExpect(jsonPath("$.customer.id", is("987e6543-e21c-12d3-a456-426614174000")))
                .andExpect(jsonPath("$.customer.name", is("John Doe")))
                .andExpect(jsonPath("$.customer.address", is("1234 Elm Street, Springfield")))

                // Invoice positions (array elements)
                .andExpect(jsonPath("$.positions[0].description", is("Consulting hours")))
                .andExpect(jsonPath("$.positions[0].amount", is(500.0)))

                // Total amount
                .andExpect(jsonPath("$.totalAmount", is(600.0)));
    }

    @Test
    public void shouldRetrieveInvoiceById() throws Exception {
        // Given data setup
        final UUID invoiceId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        final CustomerEntity customer = new CustomerEntity(UUID.fromString("987e6543-e21c-12d3-a456-426614174000"), "John Doe", "1234 Elm Street, Springfield");
        final InvoicePositionEntity invoicePosition1 = new InvoicePositionEntity("Consulting hours", 500.0F);
        final InvoicePositionEntity invoicePosition2 = new InvoicePositionEntity("Travel expenses", 100.0F);
        final InvoiceEntity invoiceEntity = new InvoiceEntity(
                invoiceId,
                "INV-2024-001",
                "Consulting Services",
                "Invoice for consulting services provided",
                LocalDateTime.parse("2024-11-05T10:00:00"),
                customer,
                Arrays.asList(invoicePosition1, invoicePosition2),
                600.0F
        );
        invoiceRepository.save(invoiceEntity);


        // Perform GET request and validate response
        mockMvc.perform(get("/api/v1/invoice/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("123e4567-e89b-12d3-a456-426614174000")))
                .andExpect(jsonPath("$.code", is("INV-2024-001")))
                .andExpect(jsonPath("$.title", is("Consulting Services")))
                .andExpect(jsonPath("$.description", is("Invoice for consulting services provided")))
                .andExpect(jsonPath("$.issuedAt", is("2024-11-05T10:00:00")))

                // Customer fields
                .andExpect(jsonPath("$.customer.id", is("987e6543-e21c-12d3-a456-426614174000")))
                .andExpect(jsonPath("$.customer.name", is("John Doe")))
                .andExpect(jsonPath("$.customer.address", is("1234 Elm Street, Springfield")))

                // Invoice positions (array elements)
                .andExpect(jsonPath("$.positions[0].description", is("Consulting hours")))
                .andExpect(jsonPath("$.positions[0].amount", is(500.0)))
                .andExpect(jsonPath("$.positions[1].description", is("Travel expenses")))
                .andExpect(jsonPath("$.positions[1].amount", is(100.0)))

                // Total amount
                .andExpect(jsonPath("$.totalAmount", is(600.0)));
    }


}
