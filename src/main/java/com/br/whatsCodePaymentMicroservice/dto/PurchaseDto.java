package com.br.whatsCodePaymentMicroservice.dto;

import com.br.whatsCodePaymentMicroservice.model.Client;
import com.br.whatsCodePaymentMicroservice.model.Installment;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class PurchaseDto {

    private Double paymentValue;
    private Date purchaseDate;

    private Set<Installment> installment = new HashSet<>();

    private Client client;

}
