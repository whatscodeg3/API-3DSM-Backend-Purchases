package com.br.whatsCodePaymentMicroservice.dto;

import com.br.whatsCodePaymentMicroservice.model.Purchase;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InstallmentDto {

    private LocalDate paymentDate;
    private BigDecimal installmentValue;
    private Boolean isInstallmentPayed;
    private Purchase purchase;
}
