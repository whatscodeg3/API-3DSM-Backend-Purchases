package com.br.whatsCodePaymentMicroservice.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReportResponse {

    private String cpf;

    private String fullName;

    private LocalDate purchaseDate;

    private BigDecimal paymentValue;

    private LocalDate installmentDueDate;

    private LocalDate paymentDate;

    private LocalDate creditDate;

    private BigDecimal installmentValue;
}
