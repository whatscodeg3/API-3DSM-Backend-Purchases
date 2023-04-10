package com.br.whatsCodePaymentMicroservice.request;

import lombok.Data;

@Data
public class InstallmentRequest {
    private String paymentDate;
    private Integer daysToCredit;
}
